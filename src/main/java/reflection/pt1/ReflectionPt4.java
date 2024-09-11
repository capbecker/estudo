package reflection.pt1;

import generico.Geral;

import java.lang.reflect.InvocationTargetException;

public class ReflectionPt4 {
    private String base;

    public ReflectionPt4() {
        this.base = "reflection.pt1";
    }

    public Object executa(String path) {
        try {
            String partesUrl[] = path.replaceFirst("/", "").split("/");
            String nomeClasse = partesUrl[0];
            String nomeController = Geral.capitalize(nomeClasse)+"Controller";
            //Class<?>.newInstance() VS Constructor<?>.newInstance()
            //(o construtor tem ExportException, como sendo um exception random)
            Class<?> classController = Class.forName(base + "." + nomeClasse + "." + nomeController);
            Object instance
//                    = classController.newInstance(); //este construtor, se der uma exception, nao possui tratamento
                    = classController.getDeclaredConstructor().newInstance(); //este construtor exige a exception a +, para exceptions imprevistas
            return instance;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            System.out.println("tratado");
        } catch (NoSuchMethodException | InvocationTargetException e) {
            System.out.println("especial");
        }
        return null;
    }
}
