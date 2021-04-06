package com.financeiro.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.financeiro.event.RecursoCriadoEvent;
import com.financeiro.model.Produto;
import com.financeiro.repository.ProdutoRepository;
import com.financeiro.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutoServices produtoServices;
		
	@Autowired
	ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Produto>getProdutos(){
						
	     return produtoRepository.findAll().stream().filter(produto -> (produto.getAtivo() !=false)).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<Produto>criar(@Valid @RequestBody Produto produto,HttpServletResponse response){
		
		Produto produtoSalvo = produtoRepository.save(produto);
		
		publisher.publishEvent(new RecursoCriadoEvent(this,response,produtoSalvo.getCodigo()));
		
     
       return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto>buscarPeloCodigo(@PathVariable Long codigo){
		return  produtoRepository.findById(codigo).map(produto -> ResponseEntity.ok(produto)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{Codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
			
		Produto produtoAdeletar =  produtoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		this.produtoRepository.delete(produtoAdeletar);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo , @Valid @RequestBody Produto produto){
		
		Produto produtoSalvo = produtoServices.atualizar(codigo, produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	public void atualizarProdAtivo(@PathVariable Long codigo,@RequestBody Boolean ativo) {
		produtoServices.atualizarProdAtivo(codigo, ativo);
	}
	
}




