package reflection.pt1.dto.delegador;

public class ManipuladorClasse extends Manipulador {
    private Class<?> classe;

    public ManipuladorClasse(Class<?> classe) {
        this.classe = classe;
    }

    public ManipuladorConstrutor getConstrutor() {
        try {
            return new ManipuladorConstrutor(classe.getDeclaredConstructor());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ManipuladorConstrutor getConstrutor(Object... params) {
        try {
            return new ManipuladorConstrutor(classe.getDeclaredConstructor(getClasses(params)),params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ManipuladorObjeto invocaInstancia() {
        Object instancia = getConstrutor().invoca();
        return new ManipuladorObjeto(instancia);
    }

    public ManipuladorObjeto invocaInstancia(Object... params) {
        Object instancia = getConstrutor(params).invoca();
        return new ManipuladorObjeto(instancia);
    }
}
