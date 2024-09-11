package reflection.pt1.produto.dto;

public class Produto extends SuperProduto {
    private String nome;
    private double valor;
    private String marca;

    public Produto(String nome, double valor, String marca) {
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "[Id="+"{}, Nome="+this.nome+", Marca="+this.marca+", Valor="+this.valor+"]";
    }
}
