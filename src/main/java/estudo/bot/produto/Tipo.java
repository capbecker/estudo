package estudo.bot.produto;

public class Tipo {
    private String marca;
    private String nome_empresa;
    private Integer codigo_barras;

    public Tipo(String marca, String nome_empresa, Integer codigo_barras) {
        this.marca = marca;
        this.nome_empresa = nome_empresa;
        this.codigo_barras = codigo_barras;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public Integer getCodigo_barras() {
        return codigo_barras;
    }
}
