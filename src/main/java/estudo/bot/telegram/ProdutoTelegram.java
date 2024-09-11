package estudo.bot.telegram;

import estudo.bot.Conversor;
import estudo.bot.produto.Tipo;

public class ProdutoTelegram extends Conversor {
    private String nome;
    private double valor;
//    private TipoProduto tipoProduto;
    private TipoTelegram tipoTelegram;

    public ProdutoTelegram(String nome, double valor, TipoTelegram tipoTelegram) {
        this.nome = nome;
        this.valor = valor;
        this.tipoTelegram = tipoTelegram;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public TipoTelegram getTipoTelegram() {
        return tipoTelegram;
    }
}
