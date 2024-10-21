package br.edu.iff.ccc.bsi.petshopvirtual.repository;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNomeContainingIgnoreCase(String nome); 
}
