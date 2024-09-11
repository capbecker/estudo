package estudo.bot.request;

import estudo.bot.Conversor;
import generico.Geral;

import java.util.HashMap;
import java.util.Map;

public class Request2 {

    private String caminhoCompleto;
    private String nomeController;
    private String nomeMetodo;
    private String plataforma;
    private Map<String,Object> queryParams = new HashMap<>();
    private Map<String,Object> queryArgs = new HashMap<>();

    public Request2(String base, String plataforma, String url) {
        // /{controller}/{method}?{nomeParam1}={param1},{nomeParam2}={param2},{nomeParam3}={param3}..
        base = base.replaceAll("/",".");
        this.plataforma = plataforma;
        String partesUrl[] = url.replaceFirst("/", "").split("[?]");
        String controleEMetodo[] = partesUrl[0].split("/");
        this.nomeMetodo = controleEMetodo[1];
        this.nomeController = Geral.capitalize(controleEMetodo[0])+"Controller";
        this.caminhoCompleto = base+"."+controleEMetodo[0]+"."+this.nomeController;

        if (partesUrl.length>1) {
            String partesParam[] = partesUrl[1].split("&");
            for (String eachParam:partesParam) {
                String[] chaveEValor = eachParam.split("=");

                String chave = chaveEValor[0];
                Object valor = Conversor.identificaEConverte(chaveEValor[1]);

                queryParams.put(chave, valor);
            }
        }
    }

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }

    public String getNomeController() {
        return nomeController;
    }

    public String getNomeMetodo() {
        return nomeMetodo;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void putParam(String chave, Object valor) {
        queryParams.put(chave,valor);
    }

    public void putArg(String chave, Object valor) {
        queryArgs.put(chave,valor);
    }

    public Boolean hasMetodo() {
        return nomeMetodo!=null&&nomeMetodo.length()>0;
    }

    public Object getQueryParam(String chave) {
        return queryParams.get(chave);
    }

    public Map<String, Object> getQueryArgs() {
        return queryArgs;
    }

    public String getPlataforma() {
        return plataforma;
    }
}
