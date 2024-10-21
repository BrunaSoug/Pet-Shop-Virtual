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
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios/view")
public class FuncionarioViewController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "funcionarios"; // Nome da página para listar funcionários
    }

    @GetMapping("/new")
    public String newFuncionarioForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "new-funcionario"; // Nome da página para o formulário de novo funcionário
    }

    @PostMapping
    public String saveFuncionario(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departamentos", departamentoRepository.findAll());
            return "new-funcionario"; // Retorna ao formulário se houver erro
        }
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios/view"; // Redireciona para a lista após salvar
    }

    @GetMapping("/edit/{id}")
    public String editFuncionarioForm(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "edit-funcionario"; // Nome da página para editar funcionário
    }

    @PostMapping("/update/{id}")
    public String updateFuncionario(@PathVariable Long id, @ModelAttribute Funcionario funcionario) {
        Funcionario existingFuncionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        existingFuncionario.setNome(funcionario.getNome());
        existingFuncionario.setEmail(funcionario.getEmail());
        existingFuncionario.setTelefone(funcionario.getTelefone());
        existingFuncionario.setEndereco(funcionario.getEndereco());
        existingFuncionario.setSalario(funcionario.getSalario());
        existingFuncionario.setCargo(funcionario.getCargo());
        existingFuncionario.setDepartamento(funcionario.getDepartamento());
        existingFuncionario.setDataContratacao(funcionario.getDataContratacao());
        funcionarioRepository.save(existingFuncionario);
        return "redirect:/funcionarios/view"; // Redireciona para a lista após atualizar
    }

    @GetMapping("/delete/{id}")
    public String deleteFuncionario(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionarios/view"; // Redireciona para a lista após deletar
    }
}
