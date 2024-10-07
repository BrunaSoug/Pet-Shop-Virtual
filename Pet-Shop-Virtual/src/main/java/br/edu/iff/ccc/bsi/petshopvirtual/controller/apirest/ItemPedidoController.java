package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repositories.ItemPedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.exceptions.ItemPedidoNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itenspedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Operation(summary = "Get an item from the order by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the item",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ItemPedido.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Item not found", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public ItemPedido findById(@Parameter(description = "ID do item de pedido a ser buscado") 
                               @PathVariable long id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException());
    }
}
