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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Obter todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários encontrada",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Usuario.class)) }),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado", 
            content = @Content)
    })
    @GetMapping
    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new UsuarioNotFoundException(); 
        }
        return usuarios;
    }

    @Operation(summary = "Obter um usuário pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Usuario.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido fornecido", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
            content = @Content)
    })
    @GetMapping("/{id}")
    public Usuario findById(@Parameter(description = "ID do usuário a ser buscado") 
                            @PathVariable long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException());
    }

    @Operation(summary = "Criar um novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Usuario.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
            content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Atualizar um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Usuario.class)) }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
            content = @Content)
    })
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException());

        existingUsuario.setLogin(usuario.getLogin());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setPermissao(usuario.getPermissao());

        return usuarioRepository.save(existingUsuario);
    }

    @Operation(summary = "Deletar um usuário pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
            content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException());
        usuarioRepository.delete(existingUsuario);
    }
}
