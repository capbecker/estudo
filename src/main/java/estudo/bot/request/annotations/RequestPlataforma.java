package estudo.bot.request.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface RequestPlataforma {
    String value(); //biblioteca
}
