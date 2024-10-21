TR10-RESTAPI-07-10-2024
package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.exception.DepartamentoNotFoundException;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Operation(summary = "Obter um departamento pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Departamento encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Departamento.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Departamento não encontrado", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> findById(@Parameter(description = "ID do departamento a ser buscado") 
                                                  @PathVariable long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).findById(id)).withSelfRel();
        Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).findAll()).withRel("departamentos");
        departamento.add(selfLink);
        departamento.add(allLink);

        return ResponseEntity.ok(departamento);
    }

    @Operation(summary = "Obter todos os departamentos")
    @GetMapping
    public List<Departamento> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();

        departamentos.forEach(departamento -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).findById(departamento.getId())).withSelfRel();
            departamento.add(selfLink);
        });

        return departamentos;
    }

    @Operation(summary = "Criar um novo departamento")
    @PostMapping
    public ResponseEntity<Departamento> create(@RequestBody Departamento departamento) {
        Departamento createdDepartamento = departamentoRepository.save(departamento);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).findById(createdDepartamento.getId())).withSelfRel();
        createdDepartamento.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartamento);
    }

    @Operation(summary = "Atualizar um departamento")
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable long id, @RequestBody Departamento departamentoDetails) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException());

        departamento.setNome(departamentoDetails.getNome());
        Departamento updatedDepartamento = departamentoRepository.save(departamento);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).findById(updatedDepartamento.getId())).withSelfRel();
        updatedDepartamento.add(selfLink);

        return ResponseEntity.ok(updatedDepartamento);
    }

    @Operation(summary = "Deletar um departamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException());

        departamentoRepository.delete(departamento);
        return ResponseEntity.noContent().build();
    }
}

package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.DepartamentoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.DepartamentoNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Operation(summary = "Get a department by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the department",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Departamento.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Department not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Departamento findById(@Parameter(description = "ID do departamento a ser buscado") 
                                 @PathVariable long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException());
    }
}
main
