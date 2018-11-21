package br.com.apirest.estudando.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.apirest.estudando.util.TipoTransacao;

@Entity(name = "transacoes")
public class Transacao implements InterfaceModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "conta_id")
	private Conta conta;
	
	@Column(name = "valor", nullable = false, precision = 11, scale = 2)
	private BigDecimal valor;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataTransacao;
	
	@Enumerated
	@Column(name = "tipo", nullable = false)
	private TipoTransacao tipoTransacao;
	
	public Transacao() { }
	
	@JsonCreator
	public Transacao(@JsonProperty("conta") Conta conta, 
					 @JsonProperty("valor") BigDecimal valor) {
		this.conta = conta;
		this.valor = valor;
		this.dataTransacao = Calendar.getInstance();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}
	
	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
	
	
	
}
