package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(summary = "Obter todos os clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado", 
            content = @Content)
    })
    @GetMapping
    public List<Cliente> findAll() throws Exception {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new Exception(); 
        }
        return clientes;
    }

    @Operation(summary = "Obter um cliente pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Cliente findById(@Parameter(description = "ID do cliente a ser buscado") 
                            @PathVariable long id) {
        return clienteRepository.findById(id)
                .orElseThrow(null);
    }

    @Operation(summary = "Criar um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
            content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Operation(summary = "Atualizar um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", 
            content = @Content)
    })
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(null);

        existingCliente.setNome(cliente.getNome());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setTelefone(cliente.getTelefone());
        existingCliente.setEndereco(cliente.getEndereco());
        existingCliente.setDataNascimento(cliente.getDataNascimento());

        return clienteRepository.save(existingCliente);
    }

    @Operation(summary = "Deletar um cliente pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", 
            content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(null);
        clienteRepository.delete(existingCliente);
    }
}
