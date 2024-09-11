package reflection.pt2.produto2;

import reflection.pt2.produto.dao.ProdutoDaoMock;
import reflection.pt2.produto.dto.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class Produto2Controller {

    private ProdutoDaoMock produtoDao;

    public Produto2Controller(ProdutoDaoMock produtoDao) {
        this.produtoDao = produtoDao;
    }

    //construtor de exemplo
    public Produto2Controller(String string) {
        System.out.println(string);
    }

    public Produto filtra() {return produtoDao.lista().stream().findAny().orElseThrow(()->new RuntimeException("erro"));}
    public List<Produto> lista() {return produtoDao.lista();}

    //com parametro
    public List<Produto> lista(String parametro) {
        return produtoDao.lista().stream().filter(p->p.getNome().equals(parametro))
                .collect(Collectors.toList());
    }
    public List<Produto> lista(String parametro, Integer numero) {
        return produtoDao.lista().stream().filter(p->p.getNome().equals(parametro) && p.getValor()==numero)
                .collect(Collectors.toList());
    }

    public Produto getProduto() {
        return new Produto("Nintendinho", 600.0, "Nintendo");
    }

    public List<Produto> getListProduto() {
        return produtoDao.lista();
    }
}
