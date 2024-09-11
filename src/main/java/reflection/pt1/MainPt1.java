package reflection.pt1;

import java.util.Scanner;

public class MainPt1 {
    //Altera a cor do texto no prompt - todo o texto permanece na cor estipulada até encontrar outro codigo ansi
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_BLUE_BACKGROUND  = "\u001B[44m"; // so coloquei pra ver que pode mudar a cor de fundo

//@RequestParam(value="key")
    public static void main(String args[]) {
        String pathFiltra = "/produto/filtra";
        String pathLista = "/produto/lista";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Pt3:");
//            String path = scanner.nextLine(); //ex: /produto/filtra
            String path = pathFiltra;
            Object invokedPt3 = new ReflectionPt3().executa(path);
            System.out.println(invokedPt3);
        } catch (Exception e) {
            System.out.println("Erro no Pt3");
        }
        try{
            System.out.println("------------------------");
            System.out.println("Pt4:");
            Object invokedPt4 = new ReflectionPt4().executa(pathFiltra);
            System.out.println(invokedPt4);
        } catch (Exception e) {
            System.out.println("Erro no Pt4");
        }
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("------------------------");
            System.out.println("Pt5:");
            Object invokedPt5 = new ReflectionPt5().executa(pathLista);
            System.out.println(invokedPt5);
        } catch (Exception e) {
            System.out.println("Erro no Pt5");
        }
        try {
            System.out.println("------------------------");
            System.out.println("Pt6:");
            Object invokedPt6_1 = new ReflectionPt6().executa1(pathLista);
            System.out.println(invokedPt6_1);
            Object invokedPt6_2 = new ReflectionPt6().executa2(pathLista);
            System.out.println(invokedPt6_2);
        } catch (Exception e) {
            System.out.println("Erro no Pt6");
        }
        try {
            System.out.println("------------------------");
            System.out.println("Pt7:");
            Object invokedPt7_1 = new ReflectionPt7().executa1(pathLista);
            System.out.println(invokedPt7_1);
            Object invokedPt7_2 = new ReflectionPt7().executa2(pathLista);
            System.out.println(invokedPt7_2);
        } catch (Exception e) {
            System.out.println("Erro no Pt7");
        }
        try {
            System.out.println("------------------------");
            System.out.println("Pt8:");
            Object invokedPt8_1 = new ReflectionPt8().executa1("/produto/lista?numero=20&parametro=Produto");
            System.out.println(invokedPt8_1);
            Object invokedPt8_2 = new ReflectionPt8().executa2("/produto/lista?numero=20&parametro=Produto");
            System.out.println(invokedPt8_2);
        } catch (Exception e) {
            System.out.println("Erro no Pt8");
        }
        try {
            System.out.println("------------------------");
            System.out.println("Pt9:");
            try {
                Object invokedPt9_1 = new ReflectionPt9().executa("/produto/comErro?numero=20&parametro=Produto");
                System.out.println(invokedPt9_1);
            } catch (RuntimeException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            }
            try {
                Object invokedPt9_2 = new ReflectionPt9().executa2("/produto/comErro?numero=20&parametro=Produto");
                System.out.println(invokedPt9_2);
            }
            catch (RuntimeException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println("Erro no Pt9");
        }
    }
}
