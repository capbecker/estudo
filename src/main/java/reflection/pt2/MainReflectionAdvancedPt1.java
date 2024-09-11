package reflection.pt2;

import reflection.pt2.annotations.NomeTagXml;
import reflection.pt2.produto.dto.Produto;

import java.lang.reflect.Field;

public class MainReflectionAdvancedPt1 {

    //Aqui começa a brincar com XML
    public static void main (String args[]) throws IllegalAccessException {
        Produto produto = new Produto("Nintendinho", 600.0, "Nintendo");
        Class<?> classe = Produto.class;
        //id é privado da superclass, portanto nao se tem acesso nem pelo getField (por so obter campos publicos)
        // nem pelo getDeclaredFiled (por so obter campos da classe corrente)
//        System.out.println(classe.getField("id"));
        for (Field f: classe.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println(f.getName()+" "+f.get(produto));
        }

        System.out.println(classe.getDeclaredAnnotation(NomeTagXml.class).outroValue());

    }
}
