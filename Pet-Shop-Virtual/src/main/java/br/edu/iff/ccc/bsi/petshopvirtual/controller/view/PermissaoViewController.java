package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Permissao;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PermissaoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/permissoes/view")
public class PermissaoViewController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @GetMapping
    public String listPermissoes(Model model) {
        model.addAttribute("permissoes", permissaoRepository.findAll());
        return "permissoes"; // Nome da view para listar permissões
    }

    @GetMapping("/new")
    public String newPermissaoForm(Model model) {
        model.addAttribute("permissao", new Permissao());
        return "new-permissao"; // Nome da view para criar uma nova permissão
    }

    @PostMapping
    public String savePermissao(@Valid @ModelAttribute("permissao") Permissao permissao,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("permissao", permissao);
            return "new-permissao"; // Retorna para o formulário em caso de erro
        }
        permissaoRepository.save(permissao);
        return "redirect:/permissoes/view"; // Redireciona para a lista de permissões
    }

    @GetMapping("/edit/{id}")
    public String editPermissaoForm(@PathVariable Long id, Model model) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão inválida: " + id));
        model.addAttribute("permissao", permissao);
        return "edit-permissao"; // Nome da view para editar uma permissão
    }

    @PostMapping("/update/{id}")
    public String updatePermissao(@PathVariable Long id, @ModelAttribute Permissao permissao) {
        Permissao existingPermissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão inválida: " + id));

        // Atualiza os campos da permissão
        existingPermissao.setNome(permissao.getNome());
        existingPermissao.setNivel(permissao.getNivel());

        permissaoRepository.save(existingPermissao);
        return "redirect:/permissoes/view"; // Redireciona para a lista de permissões
    }

    @GetMapping("/delete/{id}")
    public String deletePermissao(@PathVariable Long id) {
        permissaoRepository.deleteById(id);
        return "redirect:/permissoes/view"; // Redireciona para a lista de permissões
    }
}
