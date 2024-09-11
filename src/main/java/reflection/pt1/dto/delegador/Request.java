package reflection.pt1.dto.delegador;

import generico.Geral;

public class Request {

    private String caminhoCompleto;
    private String nomeControle;
    private String nomeMetodo;

    public Request(String base, String url) {
        String partesUrl[] = url.replaceFirst("/", "").split("/");
        this.nomeMetodo = partesUrl[1];
        this.nomeControle = Geral.capitalize(partesUrl[0])+"Controller";
        this.caminhoCompleto = base+"."+partesUrl[0]+"."+ this.nomeControle;

    }

    public String getNomeControle() {
        return nomeControle;
    }

    public String getNomeMetodo() {
        return nomeMetodo;
    }

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }
}
