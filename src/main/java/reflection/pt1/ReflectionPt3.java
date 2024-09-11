package reflection.pt1;

import generico.Geral;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionPt3 {

    private String base;

    public ReflectionPt3(String base) {
        this.base = base;
    }

    public ReflectionPt3() {
        this.base = "reflection.pt1";
    }

    public Object executa(String path) {
        String partesUrl[] = path.replaceFirst("/", "").split("/");
        String nomeClasse = partesUrl[0];
        String metodo = partesUrl[1];
        String nomeController = Geral.capitalize(nomeClasse)+"Controller";
        //instancias de classe
        try {
            Class<?> classController = Class.forName(base + "." + nomeClasse + "." + nomeController);
            Constructor<?> constructorController = classController.getDeclaredConstructor();
            Constructor<?> constructorControllerParam = classController.getDeclaredConstructor(String.class);
            constructorControllerParam.setAccessible(true);// permite executar o construtor mesmo sendo privado
            Method method = classController.getMethod(metodo);
            return method.invoke(constructorController.newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
