package com.financeiro.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Produto>criar(@RequestBody Produto produto,HttpServletResponse response){
		
		Produto produtoSalvo = produtoRepository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				   .buildAndExpand(produtoSalvo.getCodigo()).toUri();
		
       response.setHeader("Location", uri.toASCIIString());
       return ResponseEntity.created(uri).body(produtoSalvo);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto>buscarPeloCodigo(@PathVariable Long codigo){
		return  produtoRepository.findById(codigo).map(produto -> ResponseEntity.ok(produto)).orElse(ResponseEntity.notFound().build());
	}

}
