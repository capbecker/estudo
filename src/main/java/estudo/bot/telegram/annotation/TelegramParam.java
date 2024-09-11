package estudo.bot.telegram.annotation;

import estudo.bot.request.annotations.RequestParam;
import estudo.bot.request.annotations.RequestPlataforma;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@RequestParam(value = "Telegram", convert = "convert")
public @interface TelegramParam {
    String alias() default ""; // apelido da variavel
    String convert() default "convert"; // metodo de conversao
    Class<?> from() default Object.class; // tipo de onde deve ser convertido
}
