package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes"; 
    }

    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente_form";     }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
                if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Por favor, preencha todos os campos obrigatórios corretamente.");
            return "cliente_form"; //         }

                if (clienteService.findByCpf(cliente.getCpf()) != null) {
            model.addAttribute("errorMessage", "O CPF já está cadastrado.");
            return "cliente_form";         }

        clienteService.save(cliente);
        return "redirect:/clientes";     }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            model.addAttribute("errorMessage", "Cliente não encontrado com ID: " + id);
            return listarClientes(model);         }
        model.addAttribute("cliente", cliente);
        return "cliente_form";     }

    @PostMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            model.addAttribute("errorMessage", "Cliente não encontrado com ID: " + id);
            return listarClientes(model);         }
        clienteService.delete(id);
        return "redirect:/clientes";     }
}