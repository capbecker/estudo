package reflection.pt1;

import generico.Geral;
import reflection.pt1.produto.ProdutoController;

import java.rmi.server.ExportException;

public class MainReflectionPt1 {
    static final String pacoteBase = "reflection.pt1.";
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = "/produto/1";
//        semReflection(path); //ruim
        comReflection(path);

    }
    public static void semReflection(String path) throws ExportException {
        String subPath[] = path.replaceFirst("/", "").split("/");
        switch (subPath[0]) {
            case "produto":
                ProdutoController pc = new ProdutoController();
                if (subPath.length>=2) {
                    switch (subPath[1]) {
                        case "filtro":
                            pc.filtra();
                            break;
                        case "lista":
                            pc.lista();
                            break;
                    }
                }
                break;
        }
    }

    public static void comReflection(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String partes[] = path.replaceFirst("/", "").split("/");
        String classe = partes[0];
        String metodo = partes[1];
        ProdutoController pc = new ProdutoController();
        //instancias de classe
        Class<ProdutoController> controller1 = ProdutoController.class;
        Class<? extends  ProdutoController> controller2 = pc.getClass();
        Class<?> controller3 = Class.forName(pacoteBase+classe+"."+ Geral.capitalize(classe)+"Controller");

        ProdutoController teste = controller1.newInstance();
        ProdutoController teste2 = controller2.newInstance();
        ProdutoController teste3 = (ProdutoController) controller3.newInstance(); //o terceiro nao extends o ProdutoController, portanto precisa do cast
    }
}
