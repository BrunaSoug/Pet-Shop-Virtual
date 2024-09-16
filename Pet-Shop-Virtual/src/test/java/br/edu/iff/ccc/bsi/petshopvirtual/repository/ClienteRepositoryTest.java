package br.edu.iff.ccc.bsi.petshopvirtual.repository;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente clienteJoao;
    private Cliente clienteMaria;

    @BeforeEach
    public void setUp() {
        clienteJoao = new Cliente("12345678900", "João da Silva", "joao@mail.com", "123456789", "Rua 1, Cidade", LocalDate.of(1990, 5, 15));
        clienteMaria = new Cliente("98765432100", "Maria Souza", "maria@mail.com", "987654321", "Rua 2, Cidade", LocalDate.of(1985, 1, 10));

        clienteRepository.save(clienteJoao);
        clienteRepository.save(clienteMaria);
    }

    @Test
    public void testFindByNomeStartingWith() {
        List<Cliente> clientes = clienteRepository.findByNomeStartingWith("João");
        assertFalse(clientes.isEmpty(), "Deveria encontrar clientes com nome começando com 'João'");
        assertEquals(1, clientes.size(), "Deveria encontrar exatamente 1 cliente com nome começando com 'João'");
        assertEquals(clienteJoao.getNome(), clientes.get(0).getNome());
        System.out.println("Teste testFindByNomeStartingWith passou!");
    }

    @Test
    public void testFindByDataNascimentoAfter() {
        List<Cliente> clientes = clienteRepository.findByDataNascimentoAfter(LocalDate.of(1990, 1, 1));
        assertFalse(clientes.isEmpty(), "Deveria encontrar clientes nascidos após 1990-01-01");
        assertEquals(1, clientes.size(), "Deveria encontrar exatamente 1 cliente nascido após 1990-01-01");
        assertEquals(clienteJoao.getNome(), clientes.get(0).getNome());
        System.out.println("Teste testFindByDataNascimentoAfter passou!");
    }

    @Test
    public void testExistsByCpf() {
        boolean exists = clienteRepository.existsByCpf("12345678900");
        assertTrue(exists, "Deveria encontrar um cliente com o CPF '12345678900'");
        System.out.println("Teste testExistsByCpf passou!");
    }
}
