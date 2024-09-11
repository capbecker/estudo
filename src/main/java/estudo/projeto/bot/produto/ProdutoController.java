package estudo.projeto.bot.produto;

import estudo.bot.produto.Produto;
import estudo.bot.produto.Tipo;
import estudo.bot.telegram.ProdutoTelegram;
import estudo.bot.telegram.TipoTelegram;
import estudo.bot.telegram.annotation.TelegramMethod;
import estudo.bot.telegram.annotation.TelegramParam;
import estudo.bot.whatsapp.ProdutoWhats;
import estudo.bot.whatsapp.TipoWhats;
import estudo.bot.whatsapp.annotation.WhatsMethod;
import estudo.bot.whatsapp.annotation.WhatsParam;


public class ProdutoController {

    @TelegramMethod()
     public String mensagem(@TelegramParam(alias = "msg") String mensagem) {
        return mensagem;
    }

    public Integer teste () {return 0;}

    @WhatsMethod(path="estudo.bot.whatsapp.ConversorWhats")
    @TelegramMethod(path="estudo.bot.telegram.ConversorTelegram")
    public Integer teste (
        @WhatsParam(alias = "prod", from = ProdutoWhats.class)
        @TelegramParam(alias = "prod", from = ProdutoTelegram.class)
            Produto produto)
    {return 1;}

    @WhatsMethod(path="estudo.bot.whatsapp.ConversorWhats")
    @TelegramMethod(path="estudo.bot.telegram.ConversorTelegram")
    public Integer teste (
        @WhatsParam(from = TipoWhats.class)
        @TelegramParam(from = TipoTelegram.class)
            Tipo tipo)
    {return 2;}

    @WhatsMethod(path="estudo.bot.whatsapp.ConversorWhats")
    @TelegramMethod(path="estudo.bot.telegram.ConversorTelegram")
    public Integer teste (
        @TelegramParam(alias = "prod", from = ProdutoTelegram.class)
        @WhatsParam(alias = "prod", from = ProdutoWhats.class)
            Produto produto,
        @WhatsParam(from = TipoWhats.class)
        @TelegramParam(from = TipoTelegram.class)
            Tipo tipo)
    {return 3;}






//--------------------------------------------------------------
//    public Integer teste (
//        @RequestParam(
//            value = "telegram",alias = "tt", from = TipoTelegram.class, convert = "estudo.bot.telegram.ConversorTelegram.convertTipo"
//        ) Tipo tipo)
//    {return 2;}
//
//    @RequestMethod(value = "telegram", path = "estudo.bot.telegram.ConversorTelegram")
//    public Integer teste (
//        @RequestParam(
//            value = "telegram", alias = "pt", from = ProdutoTelegram.class, convert = "convertProduto"
//        ) Produto produto,
//        @RequestParam(
//            value = "telegram", alias = "tt", from = TipoTelegram.class, convert = "convertTipo"
//        ) Tipo tipo)
//    {return 3;}

}
