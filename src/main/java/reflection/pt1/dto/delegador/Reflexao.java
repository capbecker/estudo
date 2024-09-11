package reflection.pt1.dto.delegador;

public class Reflexao {

    Class<?> controller;

    public ManipuladorClasse refleteClasse(String fqn) {
        try {
            controller = Class.forName(fqn);
            return new ManipuladorClasse(controller);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Class<?> getController() {
        return controller;
    }
}
