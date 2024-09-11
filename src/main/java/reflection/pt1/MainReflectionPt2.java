package reflection.pt1;

import generico.Geral;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MainReflectionPt2 {
    static final String pacoteBase = "reflection.pt1";
    public static void main(String args[]) {
        // preferencia em usar o produto
        try (Scanner scanner = new Scanner(System.in)) {
            String path = scanner.nextLine(); //ex: /produto/filtra
            comReflection(path);
        }
    }

    public static void comReflection(String path) {
        String partesUrl[] = path.replaceFirst("/", "").split("/");
        String nomeClasse = partesUrl[0];
        String metodo = partesUrl[1];
        String nomeController = Geral.capitalize(nomeClasse)+"Controller";
        //instancias de classe
        try {
            Class<?> classController = Class.forName(pacoteBase+"."+nomeClasse+"."+ nomeController);
            Object instanciaController = classController.newInstance(); // nao é legal de ser usado
            Method method = classController.getMethod(metodo);
            Object invoked = method.invoke(instanciaController);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
