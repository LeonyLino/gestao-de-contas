package br.com.apirest.estudando.services.conta;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.PessoaDoesntExistsException;
import br.com.apirest.estudando.model.Conta;
import br.com.apirest.estudando.repository.ContaRepository;
import br.com.apirest.estudando.repository.PessoaRepository;
import br.com.apirest.estudando.services.GenericService;

@Service
public class ContaServiceImpl extends GenericService implements ContaService{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContaRepository repository;
	
	@Override
	@Transactional
	public String criaConta(String payload) throws PessoaDoesntExistsException {
		Conta conta = null;
		
		try {
			conta = mapper.readValue(payload, Conta.class);
			System.out.println("cpf" + conta.getPessoa().getCpf());
			if(pessoaRepository.findbyCpf(conta.getPessoa().getCpf()) == null) {
				throw new PessoaDoesntExistsException();
			}
			
			repository.save(conta);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "Conta cadastrada com sucesso!";
	}

	@Override
	public String consultaSaldo(Long id) throws ContaDoesntExistsException {
		Conta conta = repository.findOne(id);
		System.out.println("conta " + conta);
		if(conta == null) {
			throw new ContaDoesntExistsException();
		}
		
		return String.valueOf(conta.getSaldo());
			
	}

	@Override
	public String bloquearOuDesbloquearConta(Long id) throws ContaDoesntExistsException {
		Conta conta = repository.findOne(id);
		
		System.out.println("conta 1 " + conta);
		
		if(conta == null)
			throw new ContaDoesntExistsException();
		
		conta.setFlagAtivo(conta.isFlagAtivo() ? false : true);
		
		repository.save(conta);
		
		System.out.println("conta 2 " + conta);
		
		return "Bloqueio realizado com sucesso!";
	}

}
