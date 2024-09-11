package reflection.pt1.dto.delegador;

import java.lang.reflect.Method;

public class ManipuladorObjeto extends Manipulador{
    private Object instancia;
    private Object[] params;

    public ManipuladorObjeto(Object instancia) {
        this.instancia = instancia;
    }

    public ManipuladorObjeto(Object instancia,Object[] params) {
        this.instancia = instancia;
        this.params = params;
    }

    public ManipuladorMetodo getMetodo(String nomeMetodo) {
        try {
            Method method = instancia.getClass().getDeclaredMethod(nomeMetodo);
            return new ManipuladorMetodo(instancia, method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ManipuladorMetodo getMetodo(String nomeMetodo, Object... params) {
        try {
            Method method = instancia.getClass().getDeclaredMethod(nomeMetodo, getClasses(params));
            return new ManipuladorMetodo(instancia, method, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
