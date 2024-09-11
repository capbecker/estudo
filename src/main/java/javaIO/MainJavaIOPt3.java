package javaIO;

import dto.Cliente;
import dto.ContaCorrente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Classe que da continuidade a MainJavaIOPt2, focando nas variaveis ObjectInputStream e ObjectOutputStream
 */
public class MainJavaIOPt3 {

    public static void main (String args[]) throws IOException, ClassNotFoundException {
        Cliente cliente = new Cliente("Jozé Bezerra", "123.456.789-10", "Astronauta");
        ContaCorrente cc = new ContaCorrente(cliente, 99,256).deposita(1000d);

        // Serializa o objeto binario
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contaCorrente.bin"));
        oos.writeObject(cc);
        oos.close();

        // Desserializa arquivo binario
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contaCorrente.bin"));
        ContaCorrente retorno = (ContaCorrente) ois.readObject();
        System.out.println(retorno);
    }
}
