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

