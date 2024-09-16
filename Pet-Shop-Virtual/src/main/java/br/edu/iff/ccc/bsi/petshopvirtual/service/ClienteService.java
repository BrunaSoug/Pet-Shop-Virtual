package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteService implements ClienteServiceInterface {

    private ClienteRepository repocliente;
    public ClienteService(ClienteRepository repocliente) {
        this.repocliente = repocliente;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        try {
            return repocliente.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Cliente> obterClientePorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        try {
            return repocliente.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletarCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        try {
            if (!repocliente.existsById(id)) {
                throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado");
            }
            repocliente.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }
}
