package reflection.pt1;

import reflection.pt1.dto.delegador2.Reflexao;
import reflection.pt1.dto.delegador2.Request;
import reflection.pt1.dto.delegador3.ExecutePath;

public class ReflectionPt9 {
    private String base;

    public ReflectionPt9(String base) {
        this.base = base;
    }

    public ReflectionPt9() {
        this.base = "reflection.pt1";
    }

    public Object executa(String path) {
        // Preciso voltar ao curso alura reflection pt 1
        Request request = new Request(this.base, path);
        return new Reflexao()
                .refleteClasse(request.getCaminhoCompleto())
                .invocaInstancia()
                .getMetodo(request.getNomeMetodo(), request.getQueryParams())
                .comTratamentoDeExcecao((metodo, ex)-> {
                    throw new RuntimeException("Erro no metodo " + metodo.getName() + " da classe " +
                        metodo.getDeclaringClass().getName()+".");
                })//invokedTargetException
                .invoca();
    }

    public Object executa2(String path) {
        // NESTE EU IREI USAR UMA VERSAO MINHA DO DELEGATOR, TOMANDO COMO BASE
        // O DELEGATOR1(versão que fui aprendendo durante o curso) DELEGATOR2 (versão mais fiel ao curso)
        return new ExecutePath(this.base).tratamentoDeExcecao((metodo, ex)-> {
            throw new RuntimeException("Erro no metodo " + metodo.getName() + " da classe " +
                metodo.getDeclaringClass().getName()+".");
        }).getter(path);
    }
}
