package estudo.bot.produto;


public class Produto {
    private String nome;
    private double valor;

    private Tipo tipo_produto;

    public Produto(String nome, double valor, Tipo tipo_produto) {
        this.nome = nome;
        this.valor = valor;
        this.tipo_produto = tipo_produto;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public Tipo getTipo_produto() {
        return tipo_produto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo_produto(Tipo tipo_produto) {
        this.tipo_produto = tipo_produto;
    }
}
