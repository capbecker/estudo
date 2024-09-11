package reflection.pt1;

import reflection.pt1.dto.delegador.Reflexao;
import reflection.pt1.dto.delegador.Request;

public class ReflectionPt5 {
    private String base;

    public ReflectionPt5(String base) {
        this.base = base;
    }

    public ReflectionPt5() {
        this.base = "reflection.pt1";
    }

    public Object executa(String path) {
        // dividindo as atribuições
        Request request = new Request(this.base, path);
        Reflexao ref = new Reflexao();
        return ref.refleteClasse(request.getCaminhoCompleto()).getConstrutor().invoca();
        // esse segundo instancia nao funcionou e estou com preguiça de arrumar
//        Object instancia2 =ref.refleteClasse(request.getCaminhoCompleto()).getConstrutor("teste").invoca("teste");

    }
}
