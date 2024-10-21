package br.edu.iff.ccc.bsi.petshopvirtual.repository;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome); 
}
