package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Usuario;
import java.util.Optional;

public interface UsuarioServiceInterface {

    Usuario salvarUsuario(Usuario usuario);

    void deletarUsuario(Long id);

    Optional<Usuario> obterUsuarioPorId(Long id);
}
