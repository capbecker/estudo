package javaIO;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Classe com demonstrações referente a encoding
 */
public class MainEncoding {
    public static void main (String args[]) throws UnsupportedEncodingException {
        String letra = "º";
        System.out.println(letra.codePointAt(0));
        byte[] bytes = letra.getBytes("windows-1252");
        System.out.println("Char: "+letra);
        System.out.println("Tamanho em:");
        System.out.println("► windows-1252: "+ bytes.length);
        System.out.println("► UTF-8: " + letra.getBytes(StandardCharsets.UTF_8).length);
        System.out.println("► UTF-16: " + letra.getBytes(StandardCharsets.UTF_16).length);
        System.out.println("► ASCII: " + letra.getBytes(StandardCharsets.US_ASCII).length);
        System.out.println();

        System.out.println("windows-1252 em UTF-8: " + new String(bytes, "UTF-8"));
        System.out.println("windows-1252 em UTF-16: " + new String(bytes, "UTF_16"));
        System.out.println("windows-1252 em ASCII: " + new String(bytes, StandardCharsets.US_ASCII));

        bytes = letra.getBytes("UTF-8");
        System.out.println("UTF-8 em windows-1252: "+ new String(bytes, "windows-1252"));
        System.out.println("UTF-8 em UTF-16: "  + new String(bytes, "UTF_16"));
        System.out.println("UTF-8 em ASCII: " + new String(bytes, StandardCharsets.US_ASCII));
    }
}
