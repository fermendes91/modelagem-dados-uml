package com.fermendes.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fermendes.cursomc.domain.Categoria;
import com.fermendes.cursomc.domain.Cidade;
import com.fermendes.cursomc.domain.Cliente;
import com.fermendes.cursomc.domain.Endereco;
import com.fermendes.cursomc.domain.Estado;
import com.fermendes.cursomc.domain.ItemPedido;
import com.fermendes.cursomc.domain.Pagamento;
import com.fermendes.cursomc.domain.PagamentoBoleto;
import com.fermendes.cursomc.domain.PagamentoCartao;
import com.fermendes.cursomc.domain.Pedido;
import com.fermendes.cursomc.domain.Produto;
import com.fermendes.cursomc.domain.enums.EstadoPagamento;
import com.fermendes.cursomc.domain.enums.TipoCliente;
import com.fermendes.cursomc.repositories.CategoriaRepository;
import com.fermendes.cursomc.repositories.CidadeRepository;
import com.fermendes.cursomc.repositories.ClienteRepository;
import com.fermendes.cursomc.repositories.EnderecoRepository;
import com.fermendes.cursomc.repositories.EstadoRepository;
import com.fermendes.cursomc.repositories.ItemPedidoRepository;
import com.fermendes.cursomc.repositories.PagamentoRepository;
import com.fermendes.cursomc.repositories.PedidoRepository;
import com.fermendes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// INSERTS DE CATEGORIA E PRODUTO NA BASE DE DADOS
		
		Categoria categoria1 = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto3));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		// INSERTS DE CIDADE E ESTADO NA BASE DE DADOS
		
		Estado estado1 = new Estado(null, "Parana");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Curitiba", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
				
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		// INSERT DE CLIENTE E ENDERECO NA BASE DE DADOS
		
		Cliente cliente1 = new Cliente(null, "Fernando Mendes", "fer@email.com", "032.254.671-22", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("(41) 99875-3125", "(42) 89366-7788"));
		Cliente cliente2 = new Cliente(null, "Brastemp", "brat@email.com", "13.132.312-2213/00", TipoCliente.PESSOAJURIDICA);
		cliente2.getTelefones().addAll(Arrays.asList("(41) 3652-9966, (11) 6555-1313"));
		
		Endereco endereco1 = new Endereco(null, "Rua Amazonas", "131", "Apto. 567", "Tylapis", "85691-135", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua Professor Josafa", "9999", null, "Grunido", "99111-555", cliente1, cidade2);
		Endereco endereco3 = new Endereco(null, "Rua Coronel Fabiano Ferrer", "751", null, "Porygon", "51322-648", cliente2, cidade3);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
		
		// INSERT DE PEDIDO, ESTADO_PAGAMENTO E PAGAMENTO
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/10/2018 15:52"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("03/11/2018 20:37"), cliente1, endereco2);
		Pedido pedido3 = new Pedido(null, sdf.parse("19/12/2018 10:30"), cliente2, endereco3);
		
		Pagamento pagamento1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido2,sdf.parse("07/11/2018 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		Pagamento pagamento3 = new PagamentoBoleto(null, EstadoPagamento.CANCELADO, pedido3,sdf.parse("08/01/2019 13:50"), null);
		pedido3.setPagamento(pagamento3);
		
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		cliente2.getPedidos().addAll(Arrays.asList(pedido3));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2, pagamento3));
	
		// INSERT DE ITEM PEDIDO
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2622.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto2, 50.00, 3, 5913.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 25.99, 1, 382.00);
		ItemPedido itemPedido4 = new ItemPedido(pedido3, produto3, 15.00, 1, 351.00);
		ItemPedido itemPedido5 = new ItemPedido(pedido3, produto1, 30.00, 2, 99.99);
		ItemPedido itemPedido6 = new ItemPedido(pedido3, produto2, 5.00, 5, 566.99);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		pedido3.getItens().addAll(Arrays.asList(itemPedido4, itemPedido5, itemPedido6));
		
		produto1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido5));
		produto2.getItens().addAll(Arrays.asList(itemPedido2, itemPedido3, itemPedido6));
		produto3.getItens().addAll(Arrays.asList(itemPedido4));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3, itemPedido4, itemPedido5, itemPedido6));
		
	}

}
