package com.fermendes.cursomc;

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
import com.fermendes.cursomc.domain.Produto;
import com.fermendes.cursomc.domain.enums.TipoCliente;
import com.fermendes.cursomc.repositories.CategoriaRepository;
import com.fermendes.cursomc.repositories.CidadeRepository;
import com.fermendes.cursomc.repositories.ClienteRepository;
import com.fermendes.cursomc.repositories.EnderecoRepository;
import com.fermendes.cursomc.repositories.EstadoRepository;
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
		
	}

}
