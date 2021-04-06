package com.financeiro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tbl_categoria")

public class Categoria {
	
	public Long getCod_categoria() {
		return cod_categoria;
	}
	public void setCod_categoria(Long codigo) {
		this.cod_categoria = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_categoria;
	@NotNull
	@Size(min=3,max=20)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Produto>produtos = new ArrayList<>();
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_categoria == null) ? 0 : cod_categoria.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (cod_categoria == null) {
			if (other.cod_categoria != null)
				return false;
		} else if (!cod_categoria.equals(other.cod_categoria))
			return false;
		return true;
	}

	

}
