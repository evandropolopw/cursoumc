package br.com.evandropolo;

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
import br.com.evandropolo.domain.Produto;
import br.com.evandropolo.domain.enums.TipoCliente;
import br.com.evandropolo.reposiories.CategoriaRepository;
import br.com.evandropolo.reposiories.CidadeRepository;
import br.com.evandropolo.reposiories.ClienteRepository;
import br.com.evandropolo.reposiories.EnderecoRepository;
import br.com.evandropolo.reposiories.EstadoRepository;
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
	private EnderecoRepository enderecoRepository;

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
	}

}
