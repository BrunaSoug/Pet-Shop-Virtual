package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
