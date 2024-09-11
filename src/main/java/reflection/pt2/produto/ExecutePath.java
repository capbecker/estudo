package reflection.pt2.produto;

import reflection.pt1.dto.Conversor;
import reflection.pt1.dto.delegador3.Request;
import reflection.pt2.produto.ioc.ContainerIoC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ExecutePath {

    private String base;
    private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

    public ExecutePath(String base) {
        this.base = base;
    }

    public static Object getter(String base, String path)  {
        return new ExecutePath(base).getter(path);
    }

    public Object getter(String path)  {
        Method selecionado = null;
        try {
            Request request = new Request(base, path);                   //Classe Controller
            Object instanciaController = new ContainerIoC().getInstancia(Class.forName(request.getCaminhoCompleto()));
            if (request.hasMetodo()) {
                selecionado = getMetodo(instanciaController, request.getNomeMetodo(), request.getQueryParams());
                return invoca(instanciaController, selecionado, request.getQueryParams());
            }
            return instanciaController;
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            if (tratamentoExcecao != null && selecionado!=null) {
                return tratamentoExcecao.apply(selecionado, e);
            }
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Method getMetodo(Object instancia, String nomeMetodo, Map<String, Object> params) {
        Stream<Method> streamMetodos = Stream.of(instancia.getClass().getDeclaredMethods());
        return streamMetodos.filter(
            metodo ->
                metodo.getName().equals(nomeMetodo) &&
                    metodo.getParameterCount() == params.values().size() &&
                    Stream.of(metodo.getParameters())
                        .allMatch(arg ->
                            //O compilador, por padrão, troca o nome das variaveis para arg0, arg1, etc.
                            //para contornar isso, é necessario a seguinte configuração:
                            //File>Settings(Alt+Ctrl+S)>Build, Execution, Deployment>Compiler>Java Compiler
                            //Campo "Additional command line parameters:" :
                            //  ► "-parameters" (digitado)
                            {
                                System.out.println(arg.getName());
                                //-- Params são os parâmetros passados, args são os parâmetros do metodo analisado
                                return params.keySet().contains(arg.getName()) &&
                                    (
                                        //Se for String
                                        params.get(arg.getName()).getClass().equals(arg.getType()) ||
                                        //Se for um tipo que pode ser convertido com o metodo valueOf tendo um String como parametro
                                        Conversor.tryValueOf((String) params.get(arg.getName()), arg.getType())!=null
                                    );
                            }
                        )
        ).findFirst().orElseThrow(() -> new RuntimeException());
    }

    private static Object invoca(Object instancia, Method method, Map<String, Object> queryParams) throws InvocationTargetException, IllegalAccessException {
        List<Object> paramsMetodo = new ArrayList<>();
        Stream.of(method.getParameters())
            .forEach(p->paramsMetodo.add(queryParams.get(p.getName())));
        return queryParams == null ? method.invoke(instancia) : method.invoke(instancia,paramsMetodo.toArray());
    }

    public ExecutePath tratamentoDeExcecao
            (BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
        this.tratamentoExcecao = tratamentoExcecao;
        return this;
    }
}
