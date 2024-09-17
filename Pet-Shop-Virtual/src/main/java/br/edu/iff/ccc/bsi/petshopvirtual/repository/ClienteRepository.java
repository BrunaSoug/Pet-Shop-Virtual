package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	  @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:prefix%")
	    List<Cliente> findByNomeStartingWith(@Param("prefix") String prefix);

	    @Query("SELECT c FROM Cliente c WHERE c.dataNascimento > :date")
	    List<Cliente> findByDataNascimentoAfter(@Param("date") LocalDate date);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cliente c WHERE c.cpf = :cpf")
	    boolean existsByCpf(@Param("cpf") String cpf);
}
