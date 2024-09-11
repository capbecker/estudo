package reflection.pt1.produto.dao;

import reflection.pt1.produto.dto.Produto;

import java.util.List;

public interface ProdutoDao {
    public List<Produto> lista();
    public Produto getProduto(Integer id);
}
