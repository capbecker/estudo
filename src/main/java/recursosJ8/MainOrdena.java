package recursosJ8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainOrdena {
    // Metodo que realiza a mesma operação de formas diferentes
    public static void main(String args[]) {
        List<String> listStrings = Arrays.asList("Joana", "Amareu", "Zenaide", "Bruce", "Wóxingtong");
        //-------------------------------
        //-- Ordenação com Colections: --
        //-------------------------------
        //-- Por ordem padrão (alfabetica) --
        Collections.sort(listStrings); //----------------------------------------------------------1
        listStrings.sort(null); //--------------------------------------------------------------2
        //Por ordem personalizada (no caso, pelo tamanho)
        Collections.sort(listStrings, new ComparadorPorTamanho());//-------------------------------3
        Collections.sort(listStrings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });//--------------------------------------------------------------------------------------4

        System.out.println(listStrings);
        //-- Usando novos recursos --> Esses recursos foram add no Java 8:
        //  ► a possibilidade de uma implementação padrão em uma interface; (metodos default)
        //  ► lambda;
        //  ► metodos references.
        //-- Por ordem personalizada (no caso, pelo tamanho) --
        //Hibrido do antigo e novo
        Collections.sort(listStrings, (o1,o2)-> Integer.compare(o1.length(),o2.length())); //------5
        //Internamente, aplica do de cima
        listStrings.sort((o1, o2) -> o1.compareTo(o2));//------------------------------------------6
        listStrings.sort(new ComparadorPorTamanho());//--------------------------------------------7
        //ordenação padrão de String: (o1, o2) -> o1.compareTo(o2);
        Comparator<String> cpt = (o1, o2) -> Integer.compare(o1.length(),o2.length());
        listStrings.sort(cpt);//-------------------------------------------------------------------8
        listStrings.sort(Comparator.comparing(o->o.length()));//-----------------------------------9
        listStrings.sort(Comparator.comparing(String::length));//---------------------------------10
        Function<String, Integer> f = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Comparator<String> c = Comparator.comparing(f);
        listStrings.sort(c);//--------------------------------------------------------------------11
        Function<String, Integer> f2 = String::length;
        listStrings.sort(Comparator.comparing(f2));//---------------------------------------------12
        //-- Comparator's "pronto", na classe String --
        listStrings.sort(String.CASE_INSENSITIVE_ORDER); //---------------------------------------13
        System.out.println(listStrings);

        //------------------------
        //-- Exibição dos dados --
        //------------------------
        listStrings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        System.out.println("----------");
        listStrings.forEach(new Consumidor());
        System.out.println("----------");
        listStrings.forEach(o1->System.out.println(o1));
        System.out.println("----------");
        Consumer <String> consumer = s -> System.out.println(s);
        listStrings.forEach(consumer);
        System.out.println("----------");
        Consumer <String> consumer2 = System.out::println;
        listStrings.forEach(consumer2);
        System.out.println("----------");
        listStrings.forEach(System.out::println);

    }
}
class ComparadorPorTamanho implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(),o2.length());
    }
}

class Consumidor implements Consumer<String>{
    @Override
    public void accept(String s){
        System.out.println(s);
    }
}
