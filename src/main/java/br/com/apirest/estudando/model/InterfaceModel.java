package br.com.apirest.estudando.model;

import java.io.Serializable;

public interface InterfaceModel<ID> extends Serializable{
	
	ID getId();
	
	void setId(Long id);
}
