package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pessoa;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.PessoaRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.PessoaNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Operation(summary = "Obter uma pessoa pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Pessoa.class)) }),
        @ApiResponse(responseCode = "400", description = "Nome inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", 
            content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pessoa> findByNome(@Parameter(description = "Nome da pessoa a ser buscada") 
                                              @PathVariable String nome) {
        Pessoa pessoa = pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new PessoaNotFoundException());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).findByNome(nome)).withSelfRel();
        pessoa.add(selfLink);

        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Obter todas as pessoas")
    @GetMapping
    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        pessoas.forEach(pessoa -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).findByNome(pessoa.getNome())).withSelfRel();
            pessoa.add(selfLink);
        });

        return pessoas;
    }

    @Operation(summary = "Criar uma nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaRepository.save(pessoa);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).findByNome(createdPessoa.getNome())).withSelfRel();
        createdPessoa.add(selfLink);

        return ResponseEntity.status(201).body(createdPessoa);
    }

    @Operation(summary = "Atualizar uma pessoa")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException());

        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setCpf(pessoaDetails.getCpf());
        pessoa.setEmail(pessoaDetails.getEmail());
        pessoa.setTelefone(pessoaDetails.getTelefone());
        pessoa.setEndereco(pessoaDetails.getEndereco());

        Pessoa updatedPessoa = pessoaRepository.save(pessoa);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class).findByNome(updatedPessoa.getNome())).withSelfRel();
        updatedPessoa.add(selfLink);

        return ResponseEntity.ok(updatedPessoa);
    }

    @Operation(summary = "Deletar uma pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException());

        pessoaRepository.delete(pessoa);
        return ResponseEntity.noContent().build();
    }
}
