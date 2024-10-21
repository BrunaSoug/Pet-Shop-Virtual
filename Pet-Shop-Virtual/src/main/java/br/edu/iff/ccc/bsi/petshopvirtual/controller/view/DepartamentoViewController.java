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
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/departamentos/view")
public class DepartamentoViewController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listDepartamentos(Model model) {
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "departamentos";
    }

    @GetMapping("/new")
    public String newDepartamentoForm(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "new-departamento"; 
    }

    @PostMapping
    public String saveDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departamento", departamento);
            return "new-departamento";
        }
        departamentoRepository.save(departamento);
        return "redirect:/departamentos/view";
    }

    @GetMapping("/edit/{id}")
    public String editDepartamentoForm(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento inválido: " + id));
        model.addAttribute("departamento", departamento);
        return "edit-departamento";
    }

    @PostMapping("/update/{id}")
    public String updateDepartamento(@PathVariable Long id, @ModelAttribute Departamento departamento) {
        Departamento existingDepartamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento inválido: " + id));
        existingDepartamento.setNome(departamento.getNome());
        departamentoRepository.save(existingDepartamento);
        return "redirect:/departamentos/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartamento(@PathVariable Long id) {
        departamentoRepository.deleteById(id);
        return "redirect:/departamentos/view";
    }
}
