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
		
		Produto produtoSalvo = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
	    return produtoRepository.save(produtoSalvo);
		
	}
	
	public void atualizarProdAtivo(Long codigo, Boolean ativo) {
		
		Produto produtoSalvo =  buscarPeloCodigo(codigo);
		produtoSalvo.setAtivo(ativo);
		produtoRepository.save(produtoSalvo);
	}

	private Produto buscarPeloCodigo(Long codigo) {
		Produto produtoSalvo = produtoRepository.findById(codigo).orElseThrow(()-> new EmptyResultDataAccessException(1)) ;
		return produtoSalvo;
	}

}
