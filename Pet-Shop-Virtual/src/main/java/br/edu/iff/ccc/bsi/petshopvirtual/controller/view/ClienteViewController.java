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
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes/view") 
public class ClienteViewController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes"; 
    }

    @GetMapping("/new")
    public String newClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "new-cliente"; 
    }

    @PostMapping
public String saveCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("cliente", cliente);
        return "new-cliente";
    }
    System.out.println("Salvando cliente: " + cliente); 
    clienteRepository.save(cliente);
    return "redirect:/clientes/view";
}
    @GetMapping("/edit/{id}")
    public String editClienteForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "edit-cliente"; 
    }

    @PostMapping("/update/{id}")
    public String updateCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        existingCliente.setNome(cliente.getNome());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setTelefone(cliente.getTelefone());
        existingCliente.setEndereco(cliente.getEndereco());
        existingCliente.setDataNascimento(cliente.getDataNascimento());
        clienteRepository.save(existingCliente); 
        return "redirect:/clientes/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id); 
        return "redirect:/clientes/view";
    }
}
