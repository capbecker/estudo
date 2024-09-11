package reflection.pt1.dto.delegador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ManipuladorMetodo {
    private Object instancia;
    private Method metodo;
    private Object[] params = null;

    public ManipuladorMetodo(Object instancia, Method metodo) {
        this.instancia = instancia;
        this.metodo = metodo;
    }

    public ManipuladorMetodo(Object instancia, Method metodo, Object... params) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }

    public Object invoca() {
        try {
            return params == null ? metodo.invoke(instancia) : metodo.invoke(instancia,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro no metodo");
        }
    }
}
