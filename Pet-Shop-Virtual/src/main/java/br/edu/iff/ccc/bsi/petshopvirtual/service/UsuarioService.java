package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Usuario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        try {
            if (!usuarioRepository.existsById(id)) {
                System.err.println("Usuário com ID " + id + " não encontrado.");
                return null;
            }
            usuarioAtualizado.setId(id);
            Usuario savedUsuario = usuarioRepository.save(usuarioAtualizado);
            System.out.println("Usuário atualizado com sucesso: " + savedUsuario);
            return savedUsuario;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Usuário: " + e.getMessage());
            return null;
        }
    }
    public List<Usuario> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            System.out.println("Usuários encontrados: " + usuarios);
            return usuarios;
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return List.of();
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        try {
            List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(nome); // Presumindo que você tenha esse método no repositório
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário encontrado com o nome: " + nome);
            } else {
                System.out.println("Usuários encontrados com o nome: " + nome + " - " + usuarios);
            }
            return usuarios;
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuários por nome: " + e.getMessage());
            return List.of();
        }
    }
}
