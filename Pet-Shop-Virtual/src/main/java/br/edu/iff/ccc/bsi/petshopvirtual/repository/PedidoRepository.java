package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT ip FROM ItemPedido ip WHERE ip.produto.nome = :nomeProduto")
    List<ItemPedido> findByProdutoNome(@Param("nomeProduto") String nomeProduto);
}
