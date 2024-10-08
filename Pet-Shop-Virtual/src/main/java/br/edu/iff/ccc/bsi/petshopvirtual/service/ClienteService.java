package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repocliente;

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

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        if (id == null || clienteAtualizado == null) {
            throw new IllegalArgumentException("ID e cliente não podem ser nulos");
        }

    
        if (!repocliente.existsById(id)) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado");
        }

       
        clienteAtualizado.setId(id);
        return repocliente.save(clienteAtualizado);
    }

    public List<Cliente> obterTodosClientes() {
        try {
            return repocliente.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os clientes: " + e.getMessage(), e);
        }
    }

    public List<Cliente> buscarClientesPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        try {
            return repocliente.findByNomeContainingIgnoreCase(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar clientes por nome: " + e.getMessage(), e);
        }
    }
}
