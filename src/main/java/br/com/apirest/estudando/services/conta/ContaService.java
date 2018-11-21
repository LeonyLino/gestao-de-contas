package br.com.apirest.estudando.services.conta;

import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.PessoaDoesntExistsException;

public interface ContaService{
	
	
	public String criaConta(String payload) throws PessoaDoesntExistsException;
	public String consultaSaldo(Long id) throws ContaDoesntExistsException;
	public String bloquearOuDesbloquearConta(Long id) throws ContaDoesntExistsException;
}
