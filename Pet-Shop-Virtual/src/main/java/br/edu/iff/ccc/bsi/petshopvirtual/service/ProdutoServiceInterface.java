package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import java.util.Optional;

public interface ProdutoServiceInterface {

    Produto salvarProduto(Produto produto);

    void deletarProduto(Long id);

    Optional<Produto> obterProdutoPorId(Long id);
}
