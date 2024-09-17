package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import java.util.Optional;

public interface ItemPedidoServiceInterface {

    ItemPedido salvarItemPedido(ItemPedido itemPedido);
    void deletarItemPedido(Long id);
    Optional<ItemPedido> obterItemPedidoPorId(Long id);
}
