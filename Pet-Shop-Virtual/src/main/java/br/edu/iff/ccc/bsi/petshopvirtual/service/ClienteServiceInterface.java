package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import java.util.Optional;

public interface ClienteServiceInterface {
    Cliente salvarCliente(Cliente cliente);
    Optional<Cliente> obterClientePorId(Long id);
    void deletarCliente(Long id);
}
