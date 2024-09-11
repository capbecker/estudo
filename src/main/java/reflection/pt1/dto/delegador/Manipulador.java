package reflection.pt1.dto.delegador;

import java.util.ArrayList;
import java.util.List;

public abstract class Manipulador {
    /** Obtém um array das classes dos elementos de uma lista*/
    Class<?>[] getClasses(Object... listaObjects) {
        List<Class<?>> listaClasses = new ArrayList<>();
        for (Object obj: listaObjects) {
            listaClasses.add(obj.getClass());
        }
        return listaClasses.toArray(new Class[0]);
    } //getClasses
}
