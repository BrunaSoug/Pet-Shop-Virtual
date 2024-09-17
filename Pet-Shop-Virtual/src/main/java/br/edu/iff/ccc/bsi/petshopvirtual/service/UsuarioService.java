package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Usuario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService implements UsuarioServiceInterface {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioRepository.save(usuario);
            System.out.println("Usuário salvo com sucesso: " + savedUsuario);
            return savedUsuario;
        } catch (Exception e) {
            System.err.println("Erro ao salvar Usuário: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deletarUsuario(Long id) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                System.out.println("Usuário com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar Usuário: " + e.getMessage());
        }
    }

    @Override
    public Optional<Usuario> obterUsuarioPorId(Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                System.out.println("Usuário encontrado: " + usuario.get());
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
            return usuario;
        } catch (Exception e) {
            System.err.println("Erro ao obter Usuário por ID: " + e.getMessage());
            return Optional.empty();
        }
    }
}
