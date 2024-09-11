package reflection.pt1.dto.delegador2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {
    Constructor<?> constructor;

    public ManipuladorConstrutor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Object invoca() {
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
