package br.edu.iff.ccc.bsi.petshopvirtual.controller.apirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.exception.ProdutoNotFoundException;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/produtos") 
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Operation(summary = "Obter todos os produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos encontrada",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Produto.class))}),
        @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado",
            content = @Content)
    })
    @GetMapping
    public List<Produto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException(); 
        }
        return produtos;
    }

    @Operation(summary = "Obter um produto pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Produto.class))}),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    })
    @GetMapping("/{id}")
    public Produto findById(@Parameter(description = "ID do produto a ser buscado")
                            @PathVariable long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException());
    }

    @Operation(summary = "Criar um novo produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Produto.class))}),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
            content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @Operation(summary = "Atualizar um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Produto.class))}),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    })
    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto) {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException());

        existingProduto.setNomeProduto(produto.getNomeProduto());
        existingProduto.setCategoriaProduto(produto.getCategoriaProduto());
        existingProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        existingProduto.setPreco(produto.getPreco());

        return produtoRepository.save(existingProduto);
    }

    @Operation(summary = "Deletar um produto pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException());
        produtoRepository.delete(existingProduto);
    }
}
