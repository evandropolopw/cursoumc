package br.com.evandropolo.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evandropolo.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>  {

}