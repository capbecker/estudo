package reflection.pt1.dto.delegador;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor extends Manipulador {
    Constructor<?> constructor;
    private Object[] params = null;

    public ManipuladorConstrutor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public ManipuladorConstrutor(Constructor<?> constructor, Object[] params) {
        this.constructor = constructor;
        this.params = params;
    }

    public Object invoca() {
        try {
            return params==null?constructor.newInstance():constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
