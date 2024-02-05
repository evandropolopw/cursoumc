package br.com.evandropolo.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evandropolo.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>  {

}
