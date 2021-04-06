package com.financeiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private Double preco;
		

	@ManyToOne
	@JoinColumn(name="cod_categoria")
	private Categoria categoria;
	
	public Produto() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Produto(Long codigo, String nome, Double preco, Categoria categoria) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	    this.categoria = categoria;
	}
	

}
