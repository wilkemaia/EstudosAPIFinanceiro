package com.financeiro.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.model.Produto;
import com.financeiro.repository.ProdutoRepository;

@Service
public class ProdutoServices {
	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto atualizar(Long codigo, Produto produto) {
		
		Produto produtoSalvo = produtoRepository.findById(codigo).orElseThrow(()-> new EmptyResultDataAccessException(1)) ;
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
	    return produtoRepository.save(produtoSalvo);
		 
		
	}

}
