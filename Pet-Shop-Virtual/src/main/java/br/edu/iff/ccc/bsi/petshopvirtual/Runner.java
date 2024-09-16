package br.edu.iff.ccc.bsi.petshopvirtual;

import br.edu.iff.ccc.bsi.petshopvirtual.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ItemPedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PedidoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private ClienteRepository clienteRepository;
    private FuncionarioRepository funcionarioRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;
    private ItemPedidoRepository itemPedidoRepository;
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
