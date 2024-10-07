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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Operation(summary = "Get a permission by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the permission",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Permissao.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Permission not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Permissao findById(@Parameter(description = "ID da permissão a ser buscada") 
                              @PathVariable long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNotFoundException());
    }
}