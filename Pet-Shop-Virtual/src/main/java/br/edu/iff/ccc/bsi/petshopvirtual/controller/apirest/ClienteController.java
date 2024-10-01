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
    private ClienteService clienteService;
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
    @Operation(summary = "Registrar um novo cliente", description = "Registrar um novo cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Info errada")
            })
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<ClienteResponse> createCliente(@RequestBody @Valid ClienteRequest request) {
    Cliente cliente = new Cliente();
    cliente.setCpf(request.getCpf());
    cliente.setNome(request.getNome());
    cliente.setEmail(request.getEmail());
    cliente.setTelefone(request.getTelefone());
    cliente.setEndereco(request.getEndereco());
    cliente.setDataNascimento(request.getDataNascimento());

    cliente = clienteService.salvarCliente(cliente);
}

}