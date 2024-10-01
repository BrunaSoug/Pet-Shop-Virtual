package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.ClienteRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.ClienteNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Get a client by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the client",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Client not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Cliente findById(@Parameter(description = "ID do cliente a ser buscado") 
                            @PathVariable long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException());
    }
}