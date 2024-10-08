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
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itenspedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Operation(summary = "Obter um item do pedido pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ItemPedido.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@Parameter(description = "ID do item de pedido a ser buscado") 
                                                @PathVariable long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException());
        
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemPedidoController.class).findById(id)).withSelfRel();
        itemPedido.add(selfLink);
        
        return ResponseEntity.ok(itemPedido);
    }

    @Operation(summary = "Obter todos os itens de pedido")
    @GetMapping
    public List<ItemPedido> findAll() {
        List<ItemPedido> itensPedido = itemPedidoRepository.findAll();
        
        itensPedido.forEach(itemPedido -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemPedidoController.class).findById(itemPedido.getId())).withSelfRel();
            itemPedido.add(selfLink);
        });
        
        return itensPedido;
    }

    @Operation(summary = "Criar um novo item de pedido")
    @PostMapping
    public ResponseEntity<ItemPedido> create(@RequestBody ItemPedido itemPedido) {
        ItemPedido createdItemPedido = itemPedidoRepository.save(itemPedido);
        
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemPedidoController.class).findById(createdItemPedido.getId())).withSelfRel();
        createdItemPedido.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdItemPedido);
    }

    @Operation(summary = "Atualizar um item de pedido")
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> update(@PathVariable long id, @RequestBody ItemPedido itemPedidoDetails) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException());

        itemPedido.setQuantidade(itemPedidoDetails.getQuantidade());
        itemPedido.setProduto(itemPedidoDetails.getProduto());
        itemPedido.setPedido(itemPedidoDetails.getPedido());
        
        ItemPedido updatedItemPedido = itemPedidoRepository.save(itemPedido);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemPedidoController.class).findById(updatedItemPedido.getId())).withSelfRel();
        updatedItemPedido.add(selfLink);

        return ResponseEntity.ok(updatedItemPedido);
    }

    @Operation(summary = "Deletar um item de pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException());

        itemPedidoRepository.delete(itemPedido);
        return ResponseEntity.noContent().build();
    }
}
