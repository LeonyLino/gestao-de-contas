package br.com.apirest.estudando.services.transacao;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apirest.estudando.exceptions.ContaBlockedException;
import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.InsufficientFundsException;
import br.com.apirest.estudando.exceptions.InvalidValueException;
import br.com.apirest.estudando.exceptions.LimiteSaqueDiarioException;
import br.com.apirest.estudando.model.Conta;
import br.com.apirest.estudando.model.Transacao;
import br.com.apirest.estudando.repository.ContaRepository;
import br.com.apirest.estudando.repository.TransacaoRepository;
import br.com.apirest.estudando.services.GenericService;
import br.com.apirest.estudando.util.TipoTransacao;

@Service
public class TransacaoServiceImpl extends GenericService implements TransacaoService{
	
	@Autowired
	private TransacaoRepository repository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	
	@Override
	@Transactional
	public String deposito(String payload) throws ContaDoesntExistsException, ContaBlockedException, InvalidValueException {
		Transacao transacao = null;
		
		try {
			transacao = mapper.readValue(payload, Transacao.class);
			Conta conta = contaRepository.findOne(transacao.getConta().getId());
			
			if(conta == null)
				throw new ContaDoesntExistsException();
			
			if(!conta.isFlagAtivo())
				throw new ContaBlockedException();
			
			if(transacao.getValor().doubleValue() <= 0.0)
				throw new InvalidValueException();
			
			transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
			conta.setSaldo(conta.getSaldo().add(transacao.getValor()));
			
			contaRepository.save(conta);
			repository.save(transacao);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "Deposito realizada com sucesso!";
	}



	@Override
	@Transactional
	public String saque(String payload) throws ContaDoesntExistsException, ContaBlockedException, 
												InvalidValueException, InsufficientFundsException, LimiteSaqueDiarioException {
		Transacao transacao = null;
		
		try {
			transacao = mapper.readValue(payload, Transacao.class);
			Conta conta = contaRepository.findOne(transacao.getConta().getId());
			
			if(conta == null)
				throw new ContaDoesntExistsException();
			
			if(!conta.isFlagAtivo())
				throw new ContaBlockedException();
			
			if(transacao.getValor().doubleValue() <= 0.0)
				throw new InvalidValueException();
			
//			if(!this.isLimiteSaqueDiario(conta, transacao))
//				throw new LimiteSaqueDiarioException();
				
			if(transacao.getValor().doubleValue() > conta.getSaldo().doubleValue())
				throw new InsufficientFundsException();
			
			transacao.setTipoTransacao(TipoTransacao.SAQUE);
			conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));
			
			contaRepository.save(conta);
			repository.save(transacao);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "Saque realizado com sucesso!";
	}



	@Override
	public List<Transacao> consultaExtrato(Long id) throws ContaDoesntExistsException {
		List<Transacao> transacoes = repository.findByContaId(id);
		System.out.println("transaçoes: " + transacoes);
				
		if(transacoes.isEmpty())
			throw new ContaDoesntExistsException();
		
		return transacoes;
	}

	@Override
	public List<Transacao> consultaExtratoPorPeriodo(Long id, Calendar dataInicial, Calendar dataFinal)
			throws ContaDoesntExistsException {
		Conta conta = contaRepository.findOne(id);
		
		if(conta == null) {
			throw new ContaDoesntExistsException();
		}
			
		System.out.println("periodo "+repository.findAllByConta_IdAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(id, dataInicial, dataFinal));
				
		return repository.findAllByConta_IdAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(id, dataInicial, dataFinal);
	}
	
//	public boolean isLimiteSaqueDiario(Conta conta, Transacao saqueValor) {
//        Calendar dataInicio = Calendar.getInstance();
//        Calendar dataFim = dataInicio.add(Calendar.DATE, 1);
//        
//		Set<Transacao> saquesDeHoje = repository.findLimiteSaqueDiario(conta.getId(), 
//        		dataInicio, dataFim, TipoTransacao.SAQUE);
//        System.out.println("limite" + saquesDeHoje);
//        BigDecimal somaSaques = BigDecimal.ZERO;
//        for(Transacao transacao : saquesDeHoje) {
//        	somaSaques = somaSaques.add(transacao.getValor());
//        }
//        
//        
//        
//        return conta.getLimiteSaqueDiario().subtract(somaSaques.add(saqueValor.getValor())).doubleValue() >= 0;
//        
//	}

}
