package reflection.pt2;

public class MainPt2 {

    public static void main (String args[]) {
        System.out.println("-------------");
        System.out.println("Pt2:");
        Object refPt2_1 = new ReflectionAdvancedPt().executa("/produto/getListProduto");
        System.out.println(refPt2_1);
        Object refPt2_2 = new ReflectionAdvancedPt().executa2("/produto/getListProduto");
        System.out.println(refPt2_2);
        System.out.println("-------------");
        System.out.println("Pt3:");
        Object refPt3_1 = new ReflectionAdvancedPt2().executa("/produto2/getListProduto");
        System.out.println(refPt3_1);
        Object refPt3_2 = new ReflectionAdvancedPt2().executa2("/produto2/getListProduto");
        System.out.println(refPt3_2);

    }
}
