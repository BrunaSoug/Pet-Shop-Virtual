package br.edu.iff.ccc.bsi.petshopvirtual.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByNome(String nome);
    List<Funcionario> findByNomeContainingIgnoreCase(String nome);
}
