package reflection.pt2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface NomeTagXml {
    public String value(); // o parametro padrão passado ao utilizar esta annotation é o value, se o metodo tivesse
                           // outro nome, o construtor ficaria: @NomeTagXml({nome}="valor");

    public String outroValue() default "pao de batata";
}
