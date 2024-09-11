package estudo.bot.telegram;

import estudo.bot.Conversor;
import estudo.bot.request.annotations.RequestField;

public class TipoTelegram extends Conversor {
    private String marca;

    @RequestField("nome_empresa")
    private String nomeEmpresa;
    @RequestField("codigo_barras")
    private Integer codigoBarras;

    public TipoTelegram(String marca, String nomeEmpresa, Integer codigoBarras) {
        this.marca = marca;
        this.nomeEmpresa = nomeEmpresa;
        this.codigoBarras = codigoBarras;
    }

    public String getMarca() {
        return marca;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public Integer getCodigoBarras() {
        return codigoBarras;
    }
}
