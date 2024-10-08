package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarPedido(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.getItensPedido().clear();  
            pedidoRepository.delete(pedido); 
        } else {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
        }
    }

    public Optional<Pedido> obterPedidoPorId(Long id) {
        try {
            return pedidoRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        if (id == null || pedidoAtualizado == null) {
            throw new IllegalArgumentException("ID e Pedido não podem ser nulos");
        }

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setData(pedidoAtualizado.getData());
            pedido.setCliente(pedidoAtualizado.getCliente());
            return pedidoRepository.save(pedido);
        } else {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
        }
    }

    public List<Pedido> obterTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> buscarPedidosPorClienteId(Long clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo");
        }
        return pedidoRepository.findByClienteId(clienteId);
    }
}
