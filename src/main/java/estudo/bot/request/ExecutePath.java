package estudo.bot.request;

import estudo.bot.request.annotations.RequestParam;
import generico.Geral;
import reflection.pt1.dto.Conversor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExecutePath {

    public static Object executa(Request request) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<?>  constructor     = Class.forName(request.getCaminhoCompleto()).getDeclaredConstructor();
        Object          instController  = constructor.newInstance();//por o containerIOC
        Method          method          = getMetodo(instController, request);
//        return invoca(instController, method, request.getQueryArgs());
        return invoca(instController, method, request);
    }

    private static Method getMetodo(Object instancia, Request request) {
        String nomeMetodo = request.getNomeMetodo();
        Stream<Method> streamMetodos = Stream.of(instancia.getClass().getDeclaredMethods());
        return streamMetodos.filter(
            metodo ->
                metodo.getName().equals(nomeMetodo) &&
                    metodo.getParameterCount() == request.getQueryParams().values().size() &&
                    Stream.of(metodo.getParameters())
                        .allMatch(arg ->
                            //O compilador, por padrão, troca o nome das variaveis para arg0, arg1, etc.
                            //para contornar isso, é necessario a seguinte configuração:
                            //File>Settings(Alt+Ctrl+S)>Build, Execution, Deployment>Compiler>Java Compiler
                            //Campo "Additional command line parameters:" :
                            //  ► "-parameters" (digitado)
                            {
                                try {
                                    //-- Params são os parâmetros passados, args são os parâmetros do metodo analisado
                                    TrataParam trataParam = new TrataParam(arg, request);
                                    request.putArg(trataParam.getNomeArg(), trataParam.getParam());
                                    return request.getQueryParams().keySet().contains(trataParam.getNomeArg()) &&
                                        (
                                            Geral.coalesce2(trataParam.getMapParam().get("from"))==null ||
                                            trataParam.getMapParam().get("from").equals(
                                                request.getQueryParam(trataParam.getNomeArg()).getClass()
                                            )
                                        ) &&
                                        (
                                            trataParam.getClassParam().equals(trataParam.getClassArg()) ||
                                                //Se for um tipo que pode ser convertido com o metodo valueOf
                                                // tendo um String como parametro
                                                Conversor.tryValueOf((String) trataParam.getParam(),
                                                    trataParam.getClassArg()) != null
                                        );
                                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                                    ClassNotFoundException e) {
                                    return false;
                                }
                            }
                        )
        ).findFirst().orElseThrow(() -> new RuntimeException());
    }

    private static Object invoca(Object instancia, Method method, Request request) throws InvocationTargetException, IllegalAccessException {
        List<Object> paramsMetodo = new ArrayList<>();
        Stream.of(method.getParameters())
            .forEach(arg->{
                try {
                    TrataParam trataParam = new TrataParam(arg, request);
                    String nomeArg = Geral.coalesce2((String) trataParam.getMapParam().get("alias"), arg.getName());
                    paramsMetodo.add(request.getQueryArgs().get(nomeArg));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        return request.getQueryArgs() == null ? method.invoke(instancia) : method.invoke(instancia,paramsMetodo.toArray());
    }
//    private static Object invoca(Object instancia, Method method, Map<String, Object> queryParams) throws InvocationTargetException, IllegalAccessException {
//        List<Object> paramsMetodo = new ArrayList<>();
//        Stream.of(method.getParameters())
//            .forEach(arg->{
//                RequestParam anotacao = arg.getAnnotation(RequestParam.class);
//                String nomeArg;
//                if (anotacao!=null) {
//                    nomeArg = Geral.coalesce(anotacao.alias(),arg.getName());
//                } else {
//                    nomeArg = arg.getName();
//                }
//                paramsMetodo.add(queryParams.get(nomeArg));
//            });
//        return queryParams == null ? method.invoke(instancia) : method.invoke(instancia,paramsMetodo.toArray());
//    }


}
