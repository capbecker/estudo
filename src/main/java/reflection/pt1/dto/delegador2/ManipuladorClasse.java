package reflection.pt1.dto.delegador2;

public class ManipuladorClasse {
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

    public ManipuladorObjeto invocaInstancia() {
        Object instancia = getConstrutor().invoca();
        return new ManipuladorObjeto(instancia);
    }
}
