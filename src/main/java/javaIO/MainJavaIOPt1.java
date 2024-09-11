package javaIO;

import java.io.*;

/**
 * Classe main para estudo de Java IO, mostrando diferentes metodos de entrada e saida
 */
public class MainJavaIOPt1 {
    /**
                    Leitura:                    |              Escrita:
                     ____________               |               ______________
    Binário:        |_InputStream_|             |              |_OutputStream_|
                          ▲                     |                   ▲
                     _____|___________          |               _____|____________
                    |_FileInputStream_|         |              |_FileOutputStream_|
                                                |
     ___________________________________________|___________________________________________
                     ________                   |                  _________
    Chars:          |_Reader_|                  |                 |_Whriter_|
                     ▲      ▲                  |                  ▲       ▲
      _______________|___   _|______________    |   _______________|____   _|______________
     |_InputStreamReader_| |_BufferedReader_|   |  |_OutputStreamReader_| |_BufferedWriter_|

    */


    public static void main (String args[]) throws IOException {
        /*********
         * Entrada *
         *********/
        /** Com ferramentas baixo nivel **/
////       //-- A partir do javaIO
////        InputStream fis = new FileInputStream("javaIO.txt");
//        //-- A partir do teclado
//        InputStream fis = System.in;
//        //-- A partir da rede (sem funcionamento)
////        Socket s = new Socket();
////        InputStream fis = s.getInputStream();
//        Reader isr = new InputStreamReader(fis);
//        BufferedReader br = new BufferedReader(isr);
//        /** Com FileReader */
        FileReader fr = new FileReader("arquivo.txt");
        BufferedReader br = new BufferedReader(fr);

        /**********
         * Saida *
         **********/
        /** Com ferramentas baixo nivel **/
//        //-- Escreve em um novo javaIO
////        OutputStream fos = new FileOutputStream("arquivo2.txt");
//        //-- Escreve na tela
//        OutputStream fos = System.out;
//        //-- Manda na rede (sem funcionamento)
////        OutputStream fos = s.getOutputStream();
//        Writer osw = new OutputStreamWriter(fos);
//        BufferedWriter bw = new BufferedWriter(osw);
//
        /** Com ferramentas FileWriter **/
//        //-- Escreve na tela
//        FileWriter fw = new FileWriter("arquivo2.txt");
//        //-- o BuffereWritter é opcional, coloquei ambas as opçoes gerando 2 arquivos a efeito de teste
//        BufferedWriter bw = new BufferedWriter(new FileWriter("arquivo2-1.txt"));

        /** Com ferramentas PrintStream  **/
//        PrintStream ps = new PrintStream("arquivo2.txt");

        /** Com ferramentas PrintStream  **/
        PrintWriter ps = new PrintWriter("arquivo2.txt");

        /***********************
         * Uso das ferramentas *
         ***********************/
        /** Com ferramentas baixo nivel **/
//        String linha = br.readLine();
//        while (linha!=null && !linha.isEmpty()){
//            bw.write(linha);
//            bw.newLine();
//            //-- Esse comando executa o que esta armazenado no bw imediatamente --
//            bw.flush();
//            linha = br.readLine();
//        }
//        br.close();
//        bw.close();
//
//
        /** Com ferramentas PrintStream  **/
        String linha = br.readLine();
        while (linha!=null && !linha.isEmpty()){
            ps.println(linha);
            //-- Esse comando executa o que esta armazenado no bw imediatamente --
            linha = br.readLine();
        }
        br.close();
        ps.close();
    }
}
