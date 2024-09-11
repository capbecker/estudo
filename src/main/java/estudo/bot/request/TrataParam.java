package estudo.bot.request;

import com.sun.corba.se.impl.io.TypeMismatchException;
import estudo.bot.request.annotations.RequestParam;
import estudo.bot.request.annotations.RequestPlataforma;
import generico.Geral;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class TrataParam {

    // Esta classe é uma auxiliar para o tratamento dos dados para a execução de metodo via reflections, do qual os tipos
    // dos dados originais podem não ser iguais aos tipos de dados dos metodos
    // referencias a "arg" são sobre os parametros do metodo a ser executado
    // referencias a "param" são sobre os parametros vindo da requisição
    private Parameter arg;
    private String nomeArg;
    private Class<?> classArg;
    private Class<?> classParam;
    private Object argValue;
//    private RequestParam requestParam;
    private HashMap<String, Object> mapParam = new HashMap<>();

    public TrataParam(Parameter arg, Request request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        this.arg = arg;

        for(Annotation curAnnotation: arg.getDeclaringExecutable().getDeclaredAnnotations()) {
            RequestPlataforma requestMethod = curAnnotation.annotationType().getAnnotation(RequestPlataforma.class);
            if (checkPlataforma(requestMethod, request.getPlataforma())) {
                mapParam.put("plataforma", requestMethod.value());
                Method method = curAnnotation.annotationType().getMethod("path");
                mapParam.put("path", method.invoke(curAnnotation));
            }
        }
        if (mapParam.size()==0) {
            throw new TypeMismatchException();
        }
        for (Annotation curAnnotation : arg.getAnnotations()) {
            RequestParam requestParam = curAnnotation.annotationType().getAnnotation(RequestParam.class);
            if (requestParam != null && checkPlataforma(requestParam, request.getPlataforma())) {
                for (Method methodRequest : requestParam.getClass().getDeclaredMethods()) {
                    Optional<Method> methodCurAnnotaion = Stream.of(curAnnotation.getClass().getDeclaredMethods())
                        .filter(m2 -> m2.getName().equals(methodRequest.getName()) && m2.getParameterCount() == 0)
                        .findFirst();
                    if (methodCurAnnotaion.isPresent()) {
                        mapParam.put(methodRequest.getName(),
                            Geral.coalesce2(
                                methodCurAnnotaion.get().invoke(curAnnotation),
                                methodRequest.invoke(requestParam)
                            )
                        );
                    }
                }
            }
        }
        if (mapParam.size() > 0) {
            nomeArg = Geral.coalesce2((String) mapParam.get("alias"), arg.getName());
        } else {
            nomeArg = arg.getName();
        }
        classArg = arg.getType();
        this.setArgValue(request.getQueryParam(nomeArg));
    }

    private Boolean checkPlataforma(Annotation annotation, String plataforma) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodPlataforma = annotation.annotationType().getDeclaredMethod("value");
        if (methodPlataforma==null) {
            return false;
        } else {
            return ((String) methodPlataforma.invoke(annotation)).equals(plataforma);
        }
    }


//    public TrataParam(Parameter arg, Request request) throws InvocationTargetException, IllegalAccessException {
//            this.arg = arg;
//        this.requestParam = arg.getAnnotation(RequestParam.class);
//        this.requestMethod = arg.getDeclaringExecutable().getDeclaredAnnotation(RequestMethod.class);
//        if (requestParam!=null) {
//            nomeArg = Geral.coalesce(requestParam.alias(),arg.getName());
////            classArg = requestParam.from().equals(Object.class)?arg.getType():requestParam.from();
//            classArg = arg.getType();
//        } else {
//            nomeArg = arg.getName();
//            classArg = arg.getType();
//        }
//    }

    public Parameter getArg() {
        return arg;
    }

    public String getNomeArg() {
        return nomeArg;
    }

    public Class<?> getClassArg() {
        return classArg;
    }

    public Class<?> getClassParam() {
        return classParam;
    }

    public Object getParam() {
        return argValue;
    }

    // seta o valor e tipo do parametro a ser executado a partir do parametro da requisição
    public void setArgValue(Object param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (param!=null && mapParam.size()>0 && Geral.coalesce2(mapParam.get("convert"))!=null
                && Geral.coalesce2(mapParam.get("from"))!=null) {
            String strConversor = ((String) mapParam.get("convert")).replaceAll("/",".");
            String strConversorMetodo = strConversor.substring(strConversor.lastIndexOf(".")+1);
            if (mapParam.get("path")!=null) {
                strConversor = ((String)mapParam.get("path")).replaceAll("/", ".")+"."+strConversor;
            }
            Class<?> conversorClass = Class.forName(strConversor.substring(0,strConversor.lastIndexOf(".")));

            // Executa da classe {conversorClass} o metodo {strConversorMetodo} com parametro {param}
            // sem instanciar a classe {conversorClass} -> ou seja, static (1° parametro do invoke refere-se a instancia)
            this.argValue = conversorClass.getMethod(strConversorMetodo, param.getClass(), Class.class)
                .invoke(null, param, this.classArg);
        } else {
            this.argValue = param;
        }
        classParam = this.argValue ==null?null:this.argValue.getClass();
    }

    public HashMap<String, Object> getMapParam() {
        return mapParam;
    }

//    // seta o valor e tipo do parametro a ser executado a partir do parametro da requisição
//    public void setArgValue(Object param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
//        if (param!=null && requestParam!=null && requestParam.convert().length()>0) {
//            String strConversor = requestParam.convert().replaceAll("/",".");
//            String strConversorMetodo = strConversor.substring(strConversor.lastIndexOf(".")+1);
//            if (requestMethod!=null) {
//                strConversor = requestMethod.path().replaceAll("/", ".")+"."+strConversor;
//            }
//            Class<?> conversorClass = Class.forName(strConversor.substring(0,strConversor.lastIndexOf(".")));
//
//            // Executa da classe {conversorClass} o metodo {strConversorMetodo} com parametro {param}
//            // sem instanciar a classe {conversorClass} -> ou seja, static (1° parametro do invoke refere-se a instancia)
//            this.argValue = conversorClass.getMethod(strConversorMetodo, param.getClass(), Class.class)
//                .invoke(null, param, this.classArg);
//        } else {
//            this.argValue = param;
//        }
//        classParam = this.argValue ==null?null:this.argValue.getClass();
//    }
}
