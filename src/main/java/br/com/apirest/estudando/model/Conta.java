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

import br.com.apirest.estudando.util.TipoConta;


@Entity(name = "contas")
public class Conta implements InterfaceModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Column(name= "saldo", nullable = false, precision = 11, scale = 2)
	private BigDecimal saldo;
	
	@Column(name= "limite_saque_diario", nullable = false, precision = 11, scale = 2)
	private BigDecimal limiteSaqueDiario;
	
	private boolean flagAtivo;
	
	@Enumerated
	@Column(name = "tipo", nullable = false)
	private TipoConta tipoConta;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	
	public Conta() { }
	
	@JsonCreator
	public Conta(@JsonProperty("id") Long id, @JsonProperty("pessoa") Pessoa pessoa, 
				@JsonProperty("tipoConta") TipoConta tipoConta) {
		if(id != null) {
			this.setId(id);
		}
		
		this.pessoa = pessoa;
		this.tipoConta = tipoConta;
		this.flagAtivo = true;
		this.limiteSaqueDiario = BigDecimal.valueOf(1000.0);
		this.saldo = BigDecimal.ZERO;
		this.dataCriacao = Calendar.getInstance();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", pessoa=" + pessoa + ", saldo=" + saldo + ", limiteSaqueDiario="
				+ limiteSaqueDiario + ", flagAtivo=" + flagAtivo + ", tipoConta=" + tipoConta + ", dataCriacao="
				+ dataCriacao + "]";
	}
	
	

	

	

	
	
	
	
}
