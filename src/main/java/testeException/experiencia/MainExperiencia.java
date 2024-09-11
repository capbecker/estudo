package testeException.experiencia;

import testeException.experiencia.annotations.Parametro;
import testeException.experiencia.annotations.Tipo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Tipo
public class MainExperiencia {
    public static void main (String args[]) {
        Pessoa pessoa = new Pessoa("18/11", LocalDate.of(2000,11,18));

        System.out.println(pessoa.getNome()+" - "+pessoa.getIdade());

//        for (ExtensionDescriptor descriptor : Extensions.getExtensions()) {
//            System.out.println(descriptor);
//        }



    }

    public static void fazAlgumaCoisa(@Parametro("valor") Integer i) {

    }
}
