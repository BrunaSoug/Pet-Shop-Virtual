package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringJUnitConfig
public class ClienteServiceTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
    	 Cliente cliente1 = new Cliente("12345678901", "Carlos", "carlos@example.com", "123456789", "Rua das Flores", LocalDate.of(1990, 1, 1));
    	    Cliente cliente2 = new Cliente("12345678902", "Ana", "ana@example.com", "987654321", "Avenida Exemplo", LocalDate.of(1985, 5, 5));
    	    Cliente cliente3 = new Cliente("12345678903", "Pedro", "pedro@example.com", "112233445", "Praça Central", LocalDate.of(2000, 12, 31));
    	    Cliente cliente4 = new Cliente("12345678904", "Laura", "laura@example.com", "556677889", "Beco das Flores", LocalDate.of(1992, 7, 15));
    	    clienteRepository.save(cliente1);
    	    clienteRepository.save(cliente2);
    	    clienteRepository.save(cliente3);
    	    clienteRepository.save(cliente4);
    }

    @AfterEach
    public void tearDown() {
        clienteRepository.deleteAll();
    }

    @Test
    public void testSalvarCliente() {
        Cliente cliente = new Cliente(
            "12345678900",
            "João",
            "joao@example.com",
            "123456789",
            "Rua Exemplo",
            LocalDate.of(1990, 1, 1)
        );
        Cliente resultado = clienteService.salvarCliente(cliente);

        assertNotNull(resultado, "O cliente retornado não deve ser nulo");
        assertEquals(cliente.getNome(), resultado.getNome(), "O nome do cliente retornado está incorreto");
        System.out.println("Teste de salvar cliente passou");
    }

    @Test
    public void testObterClientePorId() {
        Cliente cliente = new Cliente("12345678900", "Maria", "maria@example.com", "987654321", "Rua Exemplo", LocalDate.of(1985, 5, 5));
        cliente = clienteService.salvarCliente(cliente);

        Optional<Cliente> resultado = clienteService.obterClientePorId(cliente.getId());
        assertTrue(resultado.isPresent(), "O cliente não foi encontrado");
        assertEquals(cliente.getNome(), resultado.get().getNome(), "O nome do cliente retornado está incorreto");
        System.out.println("Teste de obter cliente por ID passou");
    }

    @Test
    public void testDeletarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "joao@example.com", "123456789", "Rua das Flores", LocalDate.of(1990, 1, 1));
        cliente = clienteService.salvarCliente(cliente);

        clienteService.deletarCliente(cliente.getId());
        Optional<Cliente> resultado = clienteService.obterClientePorId(cliente.getId());
        assertFalse(resultado.isPresent(), "O cliente deveria ter sido deletado");
        System.out.println("Teste de deletar cliente passou");
    }

    @Test
    public void testSalvarClienteNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.salvarCliente(null);
        });
        assertEquals("Cliente não pode ser nulo", thrown.getMessage(), "A mensagem de exceção está incorreta");
        System.out.println("Teste de salvar cliente nulo passou");
    }

    @Test
    public void testObterClientePorIdNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.obterClientePorId(null);
        });
        assertEquals("ID não pode ser nulo", thrown.getMessage(), "A mensagem de exceção está incorreta");
        System.out.println("Teste de obter cliente por ID nulo passou");
    }
}
