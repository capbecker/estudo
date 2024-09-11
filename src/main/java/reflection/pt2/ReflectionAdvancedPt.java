package reflection.pt2;

import reflection.pt1.dto.delegador2.ManipuladorObjeto;
import reflection.pt1.dto.delegador2.Reflexao;
import reflection.pt1.dto.delegador2.Request;
import reflection.pt2.dto.ConversorXML;
import reflection.pt2.produto.ExecutePath;
import reflection.pt2.produto.ioc.ContainerIoC;

public class ReflectionAdvancedPt {

    private String base;
    private ContainerIoC containerIoC;

    public ReflectionAdvancedPt(String base) {
        this.base = base;
        this.containerIoC = new ContainerIoC();
    }

    public ReflectionAdvancedPt() {
        this.base = "reflection.pt2";
        this.containerIoC = new ContainerIoC();
    }

    public Object executa (String path){
        // estou utilizando como base o relexao do pt1.RelefctionPt1->Pt8
//        String path = "/produto/getListProduto";
        Request request = new Request(this.base, path);
        Class<?> classeControle = new Reflexao().getClasse(request.getCaminhoCompleto());
        containerIoC.getInstancia(classeControle);
        Object instanciaController = containerIoC.getInstancia(classeControle);
        Object invocaMetodo = new ManipuladorObjeto(instanciaController)
            .getMetodo(request.getNomeMetodo(), request.getQueryParams())
            .invoca();
        invocaMetodo = new ConversorXML().converte(invocaMetodo);
       return invocaMetodo;
    }

    public Object executa2(String path) {
        Object invocaMetodo = ExecutePath.getter(this.base,path);
        return new ConversorXML().converte(invocaMetodo);
    }

}
