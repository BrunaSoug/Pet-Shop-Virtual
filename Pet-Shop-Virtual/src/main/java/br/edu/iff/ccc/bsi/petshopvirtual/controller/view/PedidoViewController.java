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

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import jakarta.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/pedidos/view")
public class PedidoViewController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedidos"; // Nome da view para listar pedidos
    }

    @GetMapping("/new")
    public String newPedidoForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteRepository.findAll()); // Para preencher a lista de clientes
        return "new-pedido"; // Nome da view para criar um novo pedido
    }

    @PostMapping
    public String savePedido(@Valid @ModelAttribute("pedido") Pedido pedido,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pedido", pedido);
            model.addAttribute("clientes", clienteRepository.findAll());
            return "new-pedido"; // Retorna para o formulário em caso de erro
        }
        pedido.setDataPedido(LocalDate.now()); // Define a data do pedido como a data atual
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/view"; // Redireciona para a lista de pedidos
    }

    @GetMapping("/edit/{id}")
    public String editPedidoForm(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido inválido: " + id));
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteRepository.findAll()); // Para preencher a lista de clientes
        return "edit-pedido"; // Nome da view para editar um pedido
    }

    @PostMapping("/update/{id}")
    public String updatePedido(@PathVariable Long id, @ModelAttribute Pedido pedido) {
        Pedido existingPedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido inválido: " + id));
        
        // Atualiza os campos do pedido
        existingPedido.setDataPedido(pedido.getDataPedido());
        existingPedido.setValorTotal(pedido.getValorTotal());
        existingPedido.setFormaPagamento(pedido.getFormaPagamento());
        existingPedido.setFinalizado(pedido.isFinalizado());
        existingPedido.setCliente(pedido.getCliente());
        
        pedidoRepository.save(existingPedido);
        return "redirect:/pedidos/view"; // Redireciona para a lista de pedidos
    }

    @GetMapping("/delete/{id}")
    public String deletePedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos/view"; // Redireciona para a lista de pedidos
    }
}
