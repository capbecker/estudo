package javaIO;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe que da continuidade a MainJavaIOPt1, focando nas variaveis "scan"
 */
public class MainJavaIOPt2 {
    public static void main (String args[]) throws FileNotFoundException {
        // a variavel scan escaneia o arquivo como um todo
        // a variavel scanLinha cada linha, delimitando pelo caracter ","
        Scanner scan = new Scanner(new File("arquivo.csv"), "UTF-8");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
//            String elem[] = linha.split( ",");
            Scanner scanLinha = new Scanner(linha);
            scanLinha.useDelimiter(",");

            String nome = scanLinha.next();
            Integer conta = scanLinha.nextInt();
            Integer agencia = scanLinha.nextInt();
            scanLinha.useLocale(Locale.US);
            Double saldo = scanLinha.nextDouble();

            System.out.println(String.format(new Locale("pt", "BR"), // Formata o valor como no Brasil
                "Nome: %s, %03d - %08d, Valor: %1.2f", nome, conta, agencia, saldo ));
            scanLinha.close();
        }
        scan.close();
    }
}
