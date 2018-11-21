package br.com.apirest.estudando.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GenericService {
	
	protected final ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext
	protected EntityManager manager;

}
