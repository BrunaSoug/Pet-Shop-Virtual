package br.edu.iff.ccc.bsi.petshopvirtual;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Cliente;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.ItemPedido;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Pedido;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Produto;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public Runner(ClienteRepository clienteRepository,
                  FuncionarioRepository funcionarioRepository,
                  PedidoRepository pedidoRepository,
                  ProdutoRepository produtoRepository,
                  ItemPedidoRepository itemPedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente1 = new Cliente("12345678901", "João Silva", "joao@example.com", "123456789", "Rua A, 123");
        Cliente cliente2 = new Cliente("10987654321", "Ana Souza", "ana@example.com", "987654321", "Rua B, 456");
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);

        Funcionario funcionario1 = new Funcionario("11122334455", "Maria Oliveira", "maria@example.com", "555555555", "Rua C, 789", 18000, "Gerente");
        Funcionario funcionario2 = new Funcionario("22233445566", "Carlos Pereira", "carlos@example.com", "666666666", "Rua D, 101", 12000, "Atendente");
        funcionarioRepository.save(funcionario1);
        funcionarioRepository.save(funcionario2);

        Produto produto1 = new Produto("Ração Premium", "Ração", 50, 150.0);
        Produto produto2 = new Produto("Brinquedo para Cães", "Brinquedo", 200, 25.0);
        Produto produto3 = new Produto("Cama para Gatos", "Acessório", 30, 80.0);
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);

        Pedido pedido1 = new Pedido(LocalDate.now(), 175.0, "Cartão de Crédito", false, cliente1);
        Pedido pedido2 = new Pedido(LocalDate.now().minusDays(1), 105.0, "Boleto", true, cliente2);
        pedidoRepository.save(pedido1);
        pedidoRepository.save(pedido2);

        ItemPedido itemPedido1 = new ItemPedido(2, produto1, pedido1);
        ItemPedido itemPedido2 = new ItemPedido(1, produto2, pedido1);
        ItemPedido itemPedido3 = new ItemPedido(3, produto3, pedido2);
        itemPedidoRepository.save(itemPedido1);
        itemPedidoRepository.save(itemPedido2);
        itemPedidoRepository.save(itemPedido3);

        System.out.println("Clientes encontrados:");
        clienteRepository.findAll().forEach(System.out::println);

        System.out.println("Funcionários encontrados:");
        funcionarioRepository.findAll().forEach(System.out::println);

        System.out.println("Pedidos encontrados:");
        pedidoRepository.findAll().forEach(System.out::println);

        System.out.println("Produtos encontrados:");
        produtoRepository.findAll().forEach(System.out::println);

        System.out.println("Itens de pedido encontrados:");
        itemPedidoRepository.findAll().forEach(System.out::println);
    }

}
