package estudo.bot.request.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.PARAMETER})
//@Repeatable(RequestParam.List.class)
@Target({ElementType.ANNOTATION_TYPE})
public @interface RequestParam {
    String value();
    String alias() default ""; // apelido da variavel
    String convert() default ""; // metodo de conversao
    Class<?> from() default Object.class; // tipo de onde deve ser convertido

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target({ElementType.PARAMETER})
//    @interface List {
//        RequestParam[] value();
//    }
}


