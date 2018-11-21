package br.com.apirest.estudando.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirest.estudando.model.Pessoa;

@Repository
@EnableAutoConfiguration
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Query("SELECT p FROM pessoas p WHERE cpf=:cpf")
	public Pessoa findbyCpf(@Param("cpf") String cpf);
	
//	boolean existsByPessoa(String cpf);
}
