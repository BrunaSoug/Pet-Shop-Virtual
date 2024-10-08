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
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Operation(summary = "Obter um pedido pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Pedido.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@Parameter(description = "ID do pedido a ser buscado") 
                                            @PathVariable long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).findById(id)).withSelfRel();
        pedido.add(selfLink);

        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Obter todos os pedidos")
    @GetMapping
    public List<Pedido> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        
        pedidos.forEach(pedido -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).findById(pedido.getId())).withSelfRel();
            pedido.add(selfLink);
        });
        
        return pedidos;
    }

    @Operation(summary = "Criar um novo pedido")
    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido createdPedido = pedidoRepository.save(pedido);
        
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).findById(createdPedido.getId())).withSelfRel();
        createdPedido.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }

    @Operation(summary = "Atualizar um pedido")
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable long id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException());

        pedido.setDataPedido(pedidoDetails.getDataPedido());
        pedido.setValorTotal(pedidoDetails.getValorTotal());
        pedido.setFormaPagamento(pedidoDetails.getFormaPagamento());
        pedido.setFinalizado(pedidoDetails.isFinalizado());
        pedido.setCliente(pedidoDetails.getCliente());

        Pedido updatedPedido = pedidoRepository.save(pedido);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).findById(updatedPedido.getId())).withSelfRel();
        updatedPedido.add(selfLink);

        return ResponseEntity.ok(updatedPedido);
    }

    @Operation(summary = "Deletar um pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException());

        pedidoRepository.delete(pedido);
        return ResponseEntity.noContent().build();
    }
}
