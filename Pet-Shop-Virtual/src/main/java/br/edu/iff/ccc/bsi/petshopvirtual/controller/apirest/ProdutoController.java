package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;

@RestController
@RequestMapping("/Produtos")
public class ProdutoController {
    
     @Autowired
    private ProdutoRepository produtoRepository;

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the product",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Produto.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Product not found",
            content = @Content)
    })
    @GetMapping("/{id}")
    public Produto findById(@Parameter(description = "ID do produto a ser buscado")
                            @PathVariable long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException());
    }
}
