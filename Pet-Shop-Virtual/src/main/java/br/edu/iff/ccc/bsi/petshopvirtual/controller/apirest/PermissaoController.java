package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Permissao;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.PermissaoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.PermissaoNotFoundException;
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
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Operation(summary = "Obter uma permissão pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Permissão encontrada",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Permissao.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Permissão não encontrada", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Permissao> findById(@Parameter(description = "ID da permissão a ser buscada") 
                                               @PathVariable long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNotFoundException());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).findById(id)).withSelfRel();
        permissao.add(selfLink);

        return ResponseEntity.ok(permissao);
    }

    @Operation(summary = "Obter todas as permissões")
    @GetMapping
    public List<Permissao> findAll() {
        List<Permissao> permissoes = permissaoRepository.findAll();

        permissoes.forEach(permissao -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).findById(permissao.getId())).withSelfRel();
            permissao.add(selfLink);
        });

        return permissoes;
    }

    @Operation(summary = "Criar uma nova permissão")
    @PostMapping
    public ResponseEntity<Permissao> create(@RequestBody Permissao permissao) {
        Permissao createdPermissao = permissaoRepository.save(permissao);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).findById(createdPermissao.getId())).withSelfRel();
        createdPermissao.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermissao);
    }

    @Operation(summary = "Atualizar uma permissão")
    @PutMapping("/{id}")
    public ResponseEntity<Permissao> update(@PathVariable long id, @RequestBody Permissao permissaoDetails) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNotFoundException());

        permissao.setNome(permissaoDetails.getNome());
        permissao.setNivel(permissaoDetails.getNivel());

        Permissao updatedPermissao = permissaoRepository.save(permissao);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).findById(updatedPermissao.getId())).withSelfRel();
        updatedPermissao.add(selfLink);

        return ResponseEntity.ok(updatedPermissao);
    }

    @Operation(summary = "Deletar uma permissão")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNotFoundException());

        permissaoRepository.delete(permissao);
        return ResponseEntity.noContent().build();
    }
}
