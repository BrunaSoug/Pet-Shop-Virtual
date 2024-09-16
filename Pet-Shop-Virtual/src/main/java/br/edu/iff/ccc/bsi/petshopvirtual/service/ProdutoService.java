package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class ProdutoService implements ProdutoServiceInterface {

    private ProdutoRepository repoproduto;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.repoproduto = produtoRepository;
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        try {
            Produto savedProduto = repoproduto.save(produto);
            System.out.println("Produto salvo com sucesso: " + savedProduto);
            return savedProduto;
        } catch (Exception e) {
            System.err.println("Erro ao salvar Produto: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deletarProduto(Long id) {
        try {
            if (repoproduto.existsById(id)) {
                repoproduto.deleteById(id);
                System.out.println("Produto com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("Produto com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar Produto: " + e.getMessage());
        }
    }

    @Override
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
            System.err.println("Erro ao obter Produto por ID: " + e.getMessage());
            return Optional.empty();
        }
    }
}
