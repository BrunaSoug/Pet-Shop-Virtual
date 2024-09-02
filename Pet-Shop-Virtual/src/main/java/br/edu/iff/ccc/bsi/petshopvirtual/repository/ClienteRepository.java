package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
