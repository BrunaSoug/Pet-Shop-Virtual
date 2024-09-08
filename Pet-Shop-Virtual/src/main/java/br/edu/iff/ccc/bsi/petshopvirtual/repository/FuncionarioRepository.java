package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f WHERE f.departamento.nome = :nomeDepartamento")
    List<Funcionario> findByDepartamentoNome(@Param("nomeDepartamento") String nomeDepartamento);

    @Query("SELECT f FROM Funcionario f WHERE f.dataContratacao > :dataContratacao")
    List<Funcionario> findByDataContratacaoAfter(@Param("dataContratacao") LocalDate dataContratacao);
}
