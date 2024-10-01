package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.PedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.PedidoNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Operation(summary = "Get an order by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the order",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Pedido.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Order not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Pedido findById(@Parameter(description = "ID do pedido a ser buscado") 
                           @PathVariable long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException());
    }
}