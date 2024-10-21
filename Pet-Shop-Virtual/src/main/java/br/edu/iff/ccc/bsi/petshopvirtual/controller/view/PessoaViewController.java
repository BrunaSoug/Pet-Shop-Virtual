package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pessoa;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PessoaRepository; // Importe o repositório adequado
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pessoas/view")
public class PessoaViewController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public String listPessoas(Model model) {
        model.addAttribute("pessoas", pessoaRepository.findAll());
        return "pessoas"; // Nome da view para listar pessoas
    }

    @PostMapping
    public String savePessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pessoa", pessoa);
            return "new-pessoa"; // Retorna para o formulário em caso de erro
        }
        pessoaRepository.save(pessoa);
        return "redirect:/pessoas/view"; // Redireciona para a lista de pessoas
    }

    @GetMapping("/edit/{id}")
    public String editPessoaForm(@PathVariable Long id, Model model) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa inválida: " + id));
        model.addAttribute("pessoa", pessoa);
        return "edit-pessoa"; // Nome da view para editar uma pessoa
    }

    @PostMapping("/update/{id}")
    public String updatePessoa(@PathVariable Long id, @ModelAttribute Pessoa pessoa) {
        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa inválida: " + id));

        // Atualiza os campos da pessoa
        existingPessoa.setCpf(pessoa.getCpf());
        existingPessoa.setNome(pessoa.getNome());
        existingPessoa.setEmail(pessoa.getEmail());
        existingPessoa.setTelefone(pessoa.getTelefone());
        existingPessoa.setEndereco(pessoa.getEndereco());

        pessoaRepository.save(existingPessoa);
        return "redirect:/pessoas/view"; // Redireciona para a lista de pessoas
    }

    @GetMapping("/delete/{id}")
    public String deletePessoa(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
        return "redirect:/pessoas/view"; // Redireciona para a lista de pessoas
    }
}
