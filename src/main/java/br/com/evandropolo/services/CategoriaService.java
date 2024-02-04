package br.com.evandropolo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evandropolo.domain.Categoria;
import br.com.evandropolo.reposiories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional <Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
