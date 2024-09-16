package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import java.util.Optional;

public interface PedidoServiceInterface {

    Pedido salvarPedido(Pedido pedido);

    void deletarPedido(Long id);

    Optional<Pedido> obterPedidoPorId(Long id);
}
