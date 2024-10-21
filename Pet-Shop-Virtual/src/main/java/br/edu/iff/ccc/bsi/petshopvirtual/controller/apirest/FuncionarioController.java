package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.FuncionarioDTO;
import br.edu.iff.ccc.bsi.petshopvirtual.exception.FuncionarioNotFoundException;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Obter um funcionário pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FuncionarioDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Nome inválido fornecido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                    content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<FuncionarioDTO> findByNome(@Parameter(description = "Nome do funcionário a ser buscado")
                                                       @PathVariable String nome) {
        Funcionario funcionario = funcionarioRepository.findByNome(nome)
                .orElseThrow(FuncionarioNotFoundException::new);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(nome)).withSelfRel();
        funcionarioDTO.add(selfLink);

        return ResponseEntity.ok(funcionarioDTO);
    }

    @Operation(summary = "Obter todos os funcionários")
    @GetMapping
    public List<FuncionarioDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        
        return funcionarios.stream().map(funcionario -> {
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(funcionario.getNome())).withSelfRel();
            funcionarioDTO.add(selfLink);
            return funcionarioDTO;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Criar um novo funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody Funcionario funcionario) {
        Funcionario createdFuncionario = funcionarioRepository.save(funcionario);
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(createdFuncionario);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(createdFuncionario.getNome())).withSelfRel();
        funcionarioDTO.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDTO);
    }

    @Operation(summary = "Atualizar um funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable long id, @RequestBody Funcionario funcionarioDetails) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(FuncionarioNotFoundException::new);

        funcionario.setSalario(funcionarioDetails.getSalario());
        funcionario.setCargo(funcionarioDetails.getCargo());
        funcionario.setDepartamento(funcionarioDetails.getDepartamento());
        funcionario.setDataContratacao(funcionarioDetails.getDataContratacao());

        Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(updatedFuncionario);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).findByNome(updatedFuncionario.getNome())).withSelfRel();
        funcionarioDTO.add(selfLink);

        return ResponseEntity.ok(funcionarioDTO);
    }

    @Operation(summary = "Deletar um funcionário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(FuncionarioNotFoundException::new);

        funcionarioRepository.delete(funcionario);
        return ResponseEntity.noContent().build();
    }
}
