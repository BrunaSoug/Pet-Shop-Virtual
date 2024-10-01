package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Usuario;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.UsuarioRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.UsuarioNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the user",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Usuario.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Usuario findById(@Parameter(description = "ID do usuário a ser buscado") 
                            @PathVariable long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }
}