package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.FuncionarioRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.FuncionarioNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Obter um funcionário pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Funcionario.class)) }),
        @ApiResponse(responseCode = "400", description = "Nome inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", 
            content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Funcionario> findByNome(@Parameter(description = "Nome do funcionário a ser buscado") 
                                                   @PathVariable String nome) {
        Funcionario funcionario = funcionarioRepository.findByNome(nome)
                .orElseThrow(() -> new FuncionarioNotFoundException());
        
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(nome)).withSelfRel();
        funcionario.add(selfLink);
        
        return ResponseEntity.ok(funcionario);
    }

    @Operation(summary = "Obter todos os funcionários")
    @GetMapping
    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        
        funcionarios.forEach(funcionario -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(funcionario.getNome())).withSelfRel();
            funcionario.add(selfLink);
        });
        
        return funcionarios;
    }

    @Operation(summary = "Criar um novo funcionário")
    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        Funcionario createdFuncionario = funcionarioRepository.save(funcionario);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(createdFuncionario.getNome())).withSelfRel();
        createdFuncionario.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuncionario);
    }

    @Operation(summary = "Atualizar um funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable long id, @RequestBody Funcionario funcionarioDetails) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException());

        funcionario.setSalario(funcionarioDetails.getSalario());
        funcionario.setCargo(funcionarioDetails.getCargo());
        funcionario.setDepartamento(funcionarioDetails.getDepartamento());
        funcionario.setDataContratacao(funcionarioDetails.getDataContratacao());
        
        Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(updatedFuncionario.getNome())).withSelfRel();
        updatedFuncionario.add(selfLink);

        return ResponseEntity.ok(updatedFuncionario);
    }

    @Operation(summary = "Deletar um funcionário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException());

        funcionarioRepository.delete(funcionario);
        return ResponseEntity.noContent().build();
    }
}
