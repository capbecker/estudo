package estudo.projeto;

import estudo.bot.request.ExecutePath;
import estudo.bot.request.Request;
import estudo.bot.telegram.ProdutoTelegram;
import estudo.bot.telegram.TipoTelegram;
import estudo.bot.whatsapp.ProdutoWhats;
import estudo.bot.whatsapp.TipoWhats;

import java.lang.reflect.InvocationTargetException;

public class EstudoMain {

    public static void main (String args[]) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String base = "estudo/projeto/bot";
        Request request = new Request(base, "Telegram", "/produto/mensagem?msg=mensagem");
//        System.out.println(ExecutePath.executa(request));

//-- Vindo "do whatsapp"
        TipoWhats tw = new TipoWhats("marca1", "empresa1", 123);
        ProdutoWhats produtoWhats = new ProdutoWhats("Produto 1", 10, tw);
        request = new Request(base, "Whatsapp", "/produto/teste");
        request.putParam("prod", produtoWhats);
//        request.putParam("tipo", tw);

        System.out.println(ExecutePath.executa(request));

//-- Vindo "do telegram"
        TipoTelegram tt = new TipoTelegram("marca1", "empresa1", 123);
        ProdutoTelegram produtoTelegram = new ProdutoTelegram("Produto 1", 10, tt);
        request = new Request(base, "Telegram", "/produto/teste");
        request.putParam("prod", produtoTelegram);
        request.putParam("tipo", tt);

        System.out.println(ExecutePath.executa(request));

        System.out.println("---------------");
    }
}
