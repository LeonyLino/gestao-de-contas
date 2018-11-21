package br.com.apirest.estudando.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirest.estudando.model.Conta;

@Repository
@EnableAutoConfiguration
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
}
