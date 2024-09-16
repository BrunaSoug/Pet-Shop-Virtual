package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PedidoService implements PedidoServiceInterface {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
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

    @Override
    public Optional<Pedido> obterPedidoPorId(Long id) {
        try {
            return pedidoRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
