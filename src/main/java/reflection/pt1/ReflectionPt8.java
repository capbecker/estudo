package reflection.pt1;

import reflection.pt1.dto.delegador2.Reflexao;
import reflection.pt1.dto.delegador2.Request;
import reflection.pt1.dto.delegador3.ExecutePath;

public class ReflectionPt8 {
    private String base;

    public ReflectionPt8(String base) {
        this.base = base;
    }

    public ReflectionPt8() {
        this.base = "reflection.pt1";
    }

    public Object executa1(String path) {
        // NESTE EU MUDEI A CLASSE DO REFLEXAO E REQUEST PARA O ORIGINAL DO CURSO
        Request request = new Request(this.base, path);
        return new Reflexao()
                .refleteClasse(request.getCaminhoCompleto())
                .invocaInstancia()
                .getMetodo(request.getNomeMetodo(), request.getQueryParams())
                .invoca();

    }

    public Object executa2(String path) {
        // NESTE EU IREI USAR UMA VERSAO MINHA DO DELEGATOR, TOMANDO COMO BASE
        // O DELEGATOR1(versão que fui aprendendo durante o curso) DELEGATOR2 (versão mais fiel ao curso)
        return ExecutePath.getter(this.base, path);
    }
}
