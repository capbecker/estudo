package estudo.bot.telegram;

import estudo.bot.produto.Produto;
import estudo.bot.produto.Tipo;


public class ConversorTelegram {
    // Mesmo que o segundo parametro nao seja usado, é interessante mante-lo para possibilitar conversores com
    // reflection
    public static Produto convert(ProdutoTelegram pt, Class<Produto> classeProduto) {
        return new Produto(pt.getNome(), pt.getValor(), convert(pt.getTipoTelegram(), Tipo.class)
        );
    }

    public static Tipo convert(TipoTelegram tt, Class<Tipo> classeTipo) {
        return new Tipo(tt.getMarca(), tt.getNomeEmpresa(), tt.getCodigoBarras());
    }

    //O que atrapalha este metodo é o class do origem, que na hora de executar o getMethod o sistema "se confunde" no K
//    public static <T,K> T masoquista(K origem, Class<T> classeDestino) throws InvocationTargetException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        return Conversor.convert(origem, classeDestino);
//    }
}
