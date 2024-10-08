package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repoproduto;
    public Produto salvarProduto(Produto produto) {
        try {
            Produto produtoSalvo = repoproduto.save(produto);
            System.out.println("Produto salvo com sucesso: " + produtoSalvo);
            return produtoSalvo;
        } catch (Exception e) {
            System.err.println("Erro ao salvar o produto: " + e.getMessage());
            return null;
        }
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        if (!repoproduto.existsById(id)) {
            System.err.println("Produto com ID " + id + " não encontrado.");
            return null; 
        }
        produtoAtualizado.setId(id); 
        return salvarProduto(produtoAtualizado);
    }
    public void deletarProduto(Long id) {
        try {
            if (repoproduto.existsById(id)) {
                repoproduto.deleteById(id);
                System.out.println("Produto com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("Produto com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar o produto: " + e.getMessage());
        }
    }
    public Optional<Produto> obterProdutoPorId(Long id) {
        try {
            Optional<Produto> produto = repoproduto.findById(id);
            if (produto.isPresent()) {
                System.out.println("Produto encontrado: " + produto.get());
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }
            return produto;
        } catch (Exception e) {
            System.err.println("Erro ao obter o produto por ID: " + e.getMessage());
            return Optional.empty();
        }
    }
    public List<Produto> obterTodosProdutos() {
        try {
            List<Produto> produtos = repoproduto.findAll();
            System.out.println("Produtos encontrados: " + produtos);
            return produtos;
        } catch (Exception e) {
            System.err.println("Erro ao obter todos os produtos: " + e.getMessage());
            return List.of(); 
        }
    }
    public boolean existeProdutoPorId(Long id) {
        return repoproduto.existsById(id);
    }
}
