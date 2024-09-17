package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemPedidoServiceTest {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @BeforeEach
    public void limparDados() {
        itemPedidoRepository.deleteAll();
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void testSalvarItemPedido() {
    	Cliente cliente = new Cliente("12345678900", "Maria", "maria@example.com", "987654321", "Rua Exemplo", LocalDate.of(1985, 5, 5));
        Cliente savedCliente = clienteRepository.save(cliente);
        Produto produto = new Produto("Produto Teste", "Categoria A", 10, 10.00);
        Produto savedProduto = produtoRepository.save(produto);
        Pedido pedido = new Pedido();
        pedido.setValorTotal(50.00);
        pedido.setDataPedido(LocalDate.now());
        pedido.setFormaPagamento("Cartão de Crédito");
        pedido.setCliente(savedCliente);
        Pedido savedPedido = pedidoRepository.save(pedido);
        ItemPedido itemPedido = new ItemPedido(5, savedProduto, savedPedido);
        ItemPedido savedItemPedido = itemPedidoService.salvarItemPedido(itemPedido);
        assertNotNull(savedItemPedido.getId(), "O ID do ItemPedido salvo deve ser gerado.");
        assertEquals(itemPedido.getQuantidade(), savedItemPedido.getQuantidade());
        assertEquals(savedProduto.getNomeProduto(), savedItemPedido.getProduto().getNomeProduto());
        assertEquals(savedPedido.getId(), savedItemPedido.getPedido().getId());
        System.out.println("Teste de salvar ItemPedido passou.");
    }

    @Test
    public void testDeletarItemPedido() {
    	Cliente cliente = new Cliente("12345688900", "Maria tESTE", "maria@example.com", "987654321", "Rua Exemplo", LocalDate.of(1985, 5, 5));
        Cliente savedCliente = clienteRepository.save(cliente);
        Produto produto = new Produto("Mordedor", "Brinquedo", 200, 20.00);
        Produto savedProduto = produtoRepository.save(produto);
        Pedido pedido = new Pedido();
        pedido.setValorTotal(40.00);
        pedido.setDataPedido(LocalDate.now());
        pedido.setFormaPagamento("Dinheiro");
        pedido.setCliente(savedCliente);
        Pedido savedPedido = pedidoRepository.save(pedido);
        ItemPedido itemPedido = new ItemPedido(5, savedProduto, savedPedido);
        ItemPedido savedItemPedido = itemPedidoService.salvarItemPedido(itemPedido);
        itemPedidoService.deletarItemPedido(savedItemPedido.getId());
        Optional<ItemPedido> deletedItemPedido = itemPedidoRepository.findById(savedItemPedido.getId());
        assertFalse(deletedItemPedido.isPresent(), "O ItemPedido deletado deve ser ausente.");
        System.out.println("Teste de deletar ItemPedido passou.");
    }

    @Test
    public void testObterItemPedidoPorId() {
    	Cliente cliente = new Cliente("12345678900", "Maria", "maria@example.com", "987654321", "Rua Exemplo", LocalDate.of(1985, 5, 5));
        Cliente savedCliente = clienteRepository.save(cliente);
        Produto produto = new Produto("Ração", "Alimento", 20, 10.00);
        Produto savedProduto = produtoRepository.save(produto);
        Pedido pedido = new Pedido();
        pedido.setValorTotal(50.00);
        pedido.setDataPedido(LocalDate.now());
        pedido.setFormaPagamento("Transferência");
        pedido.setCliente(savedCliente);
        Pedido savedPedido = pedidoRepository.save(pedido);
        ItemPedido itemPedido = new ItemPedido(5, savedProduto, savedPedido);
        ItemPedido savedItemPedido = itemPedidoService.salvarItemPedido(itemPedido);
        Optional<ItemPedido> foundItemPedido = itemPedidoService.obterItemPedidoPorId(savedItemPedido.getId());
        assertTrue(foundItemPedido.isPresent(), "O ItemPedido deve ser encontrado.");
        assertEquals(savedItemPedido.getId(), foundItemPedido.get().getId());
        System.out.println("Teste de obter ItemPedido por ID passou.");
    }
}
