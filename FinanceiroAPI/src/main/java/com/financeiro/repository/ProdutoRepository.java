package com.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.model.Produto;

public interface ProdutoRepository  extends  JpaRepository<Produto, Long>{

}
