package estudo.bot.telegram.annotation;

import estudo.bot.request.annotations.RequestPlataforma;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RequestPlataforma(value = "Telegram")
public @interface TelegramMethod {
    String path() default "";
}
