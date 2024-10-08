package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repoitempedido;

    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        try {
            return repoitempedido.save(itemPedido);
        } catch (Exception e) {
            System.err.println("Erro ao salvar ItemPedido: " + e.getMessage());
            return null;
        }
    }

    public void deletarItemPedido(Long id) {
        try {
            if (repoitempedido.existsById(id)) {
                repoitempedido.deleteById(id);
                System.out.println("ItemPedido com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("ItemPedido com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar ItemPedido: " + e.getMessage());
        }
    }

    public Optional<ItemPedido> obterItemPedidoPorId(Long id) {
        try {
            return repoitempedido.findById(id);
        } catch (Exception e) {
            System.err.println("Erro ao obter ItemPedido por ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public ItemPedido atualizarItemPedido(Long id, ItemPedido itemPedidoAtualizado) {
        if (id == null || itemPedidoAtualizado == null) {
            throw new IllegalArgumentException("ID e ItemPedido não podem ser nulos");
        }

        if (!repoitempedido.existsById(id)) {
            throw new IllegalArgumentException("ItemPedido com ID " + id + " não encontrado");
        }

        itemPedidoAtualizado.setId(id);
        return repoitempedido.save(itemPedidoAtualizado);
    }

    public List<ItemPedido> obterTodosItensPedido() {
        try {
            return repoitempedido.findAll();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os itens de pedido: " + e.getMessage());
            throw e;
        }
    }

    public List<ItemPedido> buscarItensPedidoPorPedidoId(Long pedidoId) {
        if (pedidoId == null) {
            throw new IllegalArgumentException("ID do pedido não pode ser nulo");
        }
        try {
            return repoitempedido.findByPedidoId(pedidoId);
        } catch (Exception e) {
            System.err.println("Erro ao buscar itens de pedido por ID: " + e.getMessage());
            throw e;
        }
    }
}
