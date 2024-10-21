package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos/view")
public class ProdutoViewController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produtos"; // Nome da view para listar produtos
    }

    @GetMapping("/new")
    public String newProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "new-produto"; // Nome da view para criar um novo produto
    }

    @PostMapping
    public String saveProduto(@Valid @ModelAttribute("produto") Produto produto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("produto", produto);
            return "new-produto"; // Retorna para o formulário em caso de erro
        }
        produtoRepository.save(produto);
        return "redirect:/produtos/view"; // Redireciona para a lista de produtos
    }

    @GetMapping("/edit/{id}")
    public String editProdutoForm(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        model.addAttribute("produto", produto);
        return "edit-produto"; // Nome da view para editar um produto
    }

    @PostMapping("/update/{id}")
    public String updateProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));

        // Atualiza os campos do produto
        existingProduto.setNomeProduto(produto.getNomeProduto());
        existingProduto.setCategoriaProduto(produto.getCategoriaProduto());
        existingProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        existingProduto.setPreco(produto.getPreco());

        produtoRepository.save(existingProduto);
        return "redirect:/produtos/view"; // Redireciona para a lista de produtos
    }

    @GetMapping("/delete/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos/view"; // Redireciona para a lista de produtos
    }
}
