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
    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE 'João%'")
    List<Cliente> findByNomeStartingWith(String nome);

    @Query("SELECT c FROM Cliente c WHERE c.dataNascimento > :data")
    List<Cliente> findByDataNascimentoAfter(@Param("data") LocalDate data);
}
