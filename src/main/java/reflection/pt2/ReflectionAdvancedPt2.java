package reflection.pt2;

import reflection.pt1.dto.delegador2.ManipuladorObjeto;
import reflection.pt1.dto.delegador2.Reflexao;
import reflection.pt1.dto.delegador2.Request;
import reflection.pt2.dto.ConversorXML;
import reflection.pt2.produto.ExecutePath;
import reflection.pt2.produto.dao.ProdutoDao;
import reflection.pt2.produto.dao.ProdutoDaoMock;
import reflection.pt2.produto.ioc.ContainerIoC;

public class ReflectionAdvancedPt2 {

    private String base;
    private ContainerIoC containerIoC;

    public ReflectionAdvancedPt2(String base) {
        this.base = base;
        this.containerIoC = new ContainerIoC();
    }

    public ReflectionAdvancedPt2() {
        this.base = "reflection.pt2";
        this.containerIoC = new ContainerIoC();
    }

    public Object executa (String path){
        // estou utilizando como base o relexao do pt1.RelefctionPt1->Pt8
//        String path = "/produto2/getListProduto";
        Request request = new Request(this.base, path);
        Class<?> classeControle = new Reflexao().getClasse(request.getCaminhoCompleto());
        containerIoC.registra(ProdutoDao.class, ProdutoDaoMock.class);
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
