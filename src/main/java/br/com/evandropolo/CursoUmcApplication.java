package br.com.evandropolo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.evandropolo.domain.Categoria;
import br.com.evandropolo.domain.Cidade;
import br.com.evandropolo.domain.Cliente;
import br.com.evandropolo.domain.Endereco;
import br.com.evandropolo.domain.Estado;
import br.com.evandropolo.domain.ItemPedido;
import br.com.evandropolo.domain.Pagamento;
import br.com.evandropolo.domain.PagamentoComBoleto;
import br.com.evandropolo.domain.PagamentoComCartao;
import br.com.evandropolo.domain.Pedido;
import br.com.evandropolo.domain.Produto;
import br.com.evandropolo.domain.enums.EstadoPagamento;
import br.com.evandropolo.domain.enums.TipoCliente;
import br.com.evandropolo.reposiories.CategoriaRepository;
import br.com.evandropolo.reposiories.CidadeRepository;
import br.com.evandropolo.reposiories.ClienteRepository;
import br.com.evandropolo.reposiories.EnderecoRepository;
import br.com.evandropolo.reposiories.EstadoRepository;
import br.com.evandropolo.reposiories.ItemPedidoRepository;
import br.com.evandropolo.reposiories.PagamentoRepository;
import br.com.evandropolo.reposiories.PedidoRepository;
import br.com.evandropolo.reposiories.ProdutoRepository;

@SpringBootApplication
public class CursoUmcApplication implements CommandLineRunner{
	
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
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidosRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoUmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto (null, "Computador", 2000.0);
		Produto p2 = new Produto (null, "Impressora", 800.0);
		Produto p3 = new Produto (null, "Mouse", 80.0);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cl1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardin das Floes", "38220834", cl1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidosRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
