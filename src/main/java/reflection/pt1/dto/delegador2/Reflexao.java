package reflection.pt1.dto.delegador2;

public class Reflexao {

   public ManipuladorClasse refleteClasse(String fqn) {
        return new ManipuladorClasse(getClasse(fqn));
    }

   public Class<?> getClasse(String fqn) {
        try {
            return Class.forName(fqn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
   }
}
