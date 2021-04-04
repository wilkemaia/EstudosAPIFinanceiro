package com.financeiro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.model.Produto;
import com.financeiro.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto>getProdutos(){
		
		return produtoRepository.findAll();
	}
	

}
