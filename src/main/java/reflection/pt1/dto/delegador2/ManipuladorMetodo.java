package reflection.pt1.dto.delegador2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {
    private Object instancia;
    private Method metodo;
    private  Map<String, Object> params = null;
    private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

    public ManipuladorMetodo(Object instancia, Method metodo) {
        this.instancia = instancia;
        this.metodo = metodo;
    }

    public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }

    public Object invoca() {
        try {
            List<Object> paramsMetodo = new ArrayList<>();
            Stream.of(metodo.getParameters())
                .forEach(p->paramsMetodo.add(params.get(p.getName())));
            return params == null ? metodo.invoke(instancia) : metodo.invoke(instancia,paramsMetodo.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            // tratamento especial e customizado da exceção.
            if (tratamentoExcecao != null) {
                return tratamentoExcecao.apply(metodo, e);
            }

            e.printStackTrace();
            throw new RuntimeException("Erro no metodo");
        }
    }

    public ManipuladorMetodo comTratamentoDeExcecao
            (BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
        this.tratamentoExcecao = tratamentoExcecao;
        return this;
    }


}
