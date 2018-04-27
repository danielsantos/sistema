package com.aplinotech.cadastrocliente.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = true)
	private String codigo;
	
	@Column(nullable = true)
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private Long quantidadeTotal = 0L; // TODO alterar nome, passar para qtdEmEstoque

	@Column
	private BigDecimal custoUnitario = BigDecimal.ZERO;
	
	@Column
	private BigDecimal valorVendaUnitario = BigDecimal.ZERO;
	
	@Column(nullable = true, length = 1)
	private String status;
	
	@Transient
	private Integer qtdParaBaixa = 0;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Long quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public BigDecimal getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(BigDecimal custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getValorVendaUnitario() {
		return valorVendaUnitario;
	}

	public void setValorVendaUnitario(BigDecimal valorVendaUnitario) {
		this.valorVendaUnitario = valorVendaUnitario;
	}

	public Integer getQtdParaBaixa() {
		return qtdParaBaixa;
	}

	public void setQtdParaBaixa(Integer qtdParaBaixa) {
		this.qtdParaBaixa = qtdParaBaixa;
	}
	
}
