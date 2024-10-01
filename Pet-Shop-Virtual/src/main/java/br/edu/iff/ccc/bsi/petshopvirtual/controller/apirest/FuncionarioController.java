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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Get a employee by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the employee",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Funcionario.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Employee not found", 
            content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public Funcionario findByNome(@Parameter(description = "Nome do funcionário a ser buscado") 
                                  @PathVariable String nome) {
        return funcionarioRepository.findByNome(nome)
                .orElseThrow(() -> new FuncionarioNotFoundException());
    }
}

