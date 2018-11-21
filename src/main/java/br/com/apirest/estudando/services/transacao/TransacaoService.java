package br.com.apirest.estudando.services.transacao;

import java.util.Calendar;
import java.util.List;

import br.com.apirest.estudando.exceptions.ContaBlockedException;
import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.InsufficientFundsException;
import br.com.apirest.estudando.exceptions.InvalidValueException;
import br.com.apirest.estudando.exceptions.LimiteSaqueDiarioException;
import br.com.apirest.estudando.model.Transacao;

public interface TransacaoService {
	
	public String deposito(String payload) throws ContaDoesntExistsException, ContaBlockedException, 
												InvalidValueException;
	public String saque(String payload) throws ContaDoesntExistsException, ContaBlockedException, 
												InvalidValueException, InsufficientFundsException, LimiteSaqueDiarioException;
	
	public List<Transacao> consultaExtrato(Long id) throws ContaDoesntExistsException;
	
	public List<Transacao> consultaExtratoPorPeriodo(Long id, Calendar dataInicial, Calendar dataFinal) throws ContaDoesntExistsException;
	
	
}
