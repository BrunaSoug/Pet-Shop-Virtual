package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/itens-pedido/view")
public class ItemPedidoViewController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String listItemPedidos(Model model) {
        model.addAttribute("itensPedido", itemPedidoRepository.findAll());
        return "itens-pedido"; // Nome da view para listar itens de pedido
    }

    @GetMapping("/new")
    public String newItemPedidoForm(Model model) {
        model.addAttribute("itemPedido", new ItemPedido());
        model.addAttribute("produtos", produtoRepository.findAll()); // Para preencher a lista de produtos
        model.addAttribute("pedidos", pedidoRepository.findAll()); // Para preencher a lista de pedidos
        return "new-item-pedido"; // Nome da view para criar um novo item de pedido
    }

    @PostMapping
    public String saveItemPedido(@Valid @ModelAttribute("itemPedido") ItemPedido itemPedido,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("itemPedido", itemPedido);
            model.addAttribute("produtos", produtoRepository.findAll());
            model.addAttribute("pedidos", pedidoRepository.findAll());
            return "new-item-pedido"; // Retorna para o formulário em caso de erro
        }
        System.out.println("Salvando item de pedido: " + itemPedido);
        itemPedidoRepository.save(itemPedido);
        return "redirect:/itens-pedido/view"; // Redireciona para a lista de itens de pedido
    }

    @GetMapping("/edit/{id}")
    public String editItemPedidoForm(@PathVariable Long id, Model model) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item de pedido inválido: " + id));
        model.addAttribute("itemPedido", itemPedido);
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "edit-item-pedido"; // Nome da view para editar um item de pedido
    }

    @PostMapping("/update/{id}")
    public String updateItemPedido(@PathVariable Long id, @ModelAttribute ItemPedido itemPedido) {
        ItemPedido existingItemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item de pedido inválido: " + id));
        
        // Atualiza os campos do item de pedido
        existingItemPedido.setQuantidade(itemPedido.getQuantidade());
        existingItemPedido.setProduto(itemPedido.getProduto());
        existingItemPedido.setPedido(itemPedido.getPedido());
        
        itemPedidoRepository.save(existingItemPedido);
        return "redirect:/itens-pedido/view"; // Redireciona para a lista de itens de pedido
    }

    @GetMapping("/delete/{id}")
    public String deleteItemPedido(@PathVariable Long id) {
        itemPedidoRepository.deleteById(id);
        return "redirect:/itens-pedido/view"; // Redireciona para a lista de itens de pedido
    }
}
