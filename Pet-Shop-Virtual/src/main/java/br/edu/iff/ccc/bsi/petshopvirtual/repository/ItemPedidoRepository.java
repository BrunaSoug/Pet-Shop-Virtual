package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    @Query("SELECT ip FROM ItemPedido ip WHERE ip.pedido.numeroPedido = :numeroPedido")
    List<ItemPedido> findByPedidoNumeroPedido(@Param("numeroPedido") String numeroPedido);
}
