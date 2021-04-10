package com.financeiro.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> Logradouro;
	public static volatile SingularAttribute<Endereco, String> numero;
	public static volatile SingularAttribute<Endereco, String> Complemento;
	public static volatile SingularAttribute<Endereco, String> Bairro;
	public static volatile SingularAttribute<Endereco, String> Cep;
	public static volatile SingularAttribute<Endereco, String> Cidade;
	public static volatile SingularAttribute<Endereco, String> Estado;

	public static final String LOGRADOURO = "Logradouro";
	public static final String NUMERO = "numero";
	public static final String COMPLEMENTO = "Complemento";
	public static final String BAIRRO = "Bairro";
	public static final String CEP = "Cep";
	public static final String CIDADE = "Cidade";
	public static final String ESTADO = "Estado";

}

