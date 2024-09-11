package reflection.pt1.dto;

import generico.Geral;

import java.lang.reflect.InvocationTargetException;

public class Conversor {

    public static Object identificaEConverte(String var) {
       return Geral.coalesce(
           tryValueOf(var, Integer.class),
           tryValueOf(var, Float.class),
           tryValueOf(var, Double.class),
           tryValueOf(var, Long.class),
           var
        );
    }

    public static Object tryValueOf(String var, Class<?> numero) {
        try {
            // Metodo estatico
            return numero.getMethod("valueOf",String.class).invoke(null,var);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
            System.out.print(numero.getName()+": ");
            e.printStackTrace();
        }
        return null;
    }
}
