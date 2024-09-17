package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ItemPedidoService implements ItemPedidoServiceInterface {

    @Autowired
    private ItemPedidoRepository repoitempedido;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.repoitempedido = itemPedidoRepository;
    }

    @Override
    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        try {
            return repoitempedido.save(itemPedido);
        } catch (Exception e) {
            System.err.println("Erro ao salvar ItemPedido: " + e.getMessage());
            return null;
        }
    }

    @Override
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

    @Override
    public Optional<ItemPedido> obterItemPedidoPorId(Long id) {
        try {
            return repoitempedido.findById(id);
        } catch (Exception e) {
            System.err.println("Erro ao obter ItemPedido por ID: " + e.getMessage());
            return Optional.empty();
        }
    }
}
