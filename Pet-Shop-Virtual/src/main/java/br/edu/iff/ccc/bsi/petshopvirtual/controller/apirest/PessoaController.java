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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Operation(summary = "Get a person by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the person",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Pessoa.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Person not found", 
            content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public Pessoa findByNome(@Parameter(description = "Nome da pessoa a ser buscada") 
                             @PathVariable String nome) {
        return pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new PessoaNotFoundException());
    }
}