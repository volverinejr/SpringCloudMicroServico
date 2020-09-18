package com.claudemirojr.app.produtos.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private BigDecimal preco;

	@Column(name = "criado_em", updatable = false)
	private OffsetDateTime criadoEm;

	@PrePersist
	public void prePersist() {
		this.criadoEm = OffsetDateTime.now();
	}

	public void Atualizar(String nome, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public OffsetDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(OffsetDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

}
