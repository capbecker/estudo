package reflection.pt1.dto.delegador2;

import generico.Geral;
import reflection.pt1.dto.Conversor;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String caminhoCompleto;
    private String nomeControle;
    private String nomeMetodo;
    private Map<String, Object> queryParams;

    public Request(String base, String url) {
        // /{controller}/{method}?{nomeParam1}={param1},{nomeParam2}={param2},{nomeParam3}={param3}...
        String partesUrl[] = url.replaceFirst("/", "")
            .split("[?]");
        String controleEMetodo[] = partesUrl[0].split("/");
        this.nomeMetodo = controleEMetodo[1];
        this.nomeControle = Geral.capitalize(controleEMetodo[0])+"Controller";
        this.caminhoCompleto = base+"."+controleEMetodo[0]+"."+ this.nomeControle;

        this.queryParams = new HashMap<>();
        if (partesUrl.length>1) {
            String partesParam[] = partesUrl[1].split("&");
            for(String eachParam:partesParam) {
                String[] chaveEValor = eachParam.split("=");

                String chave = chaveEValor[0];
                Object valor = Conversor.identificaEConverte(chaveEValor[1]);

                queryParams.put(chave, valor);
            }
        }
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

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }
}
