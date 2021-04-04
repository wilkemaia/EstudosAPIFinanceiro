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

import com.financeiro.model.Pessoa;
import com.financeiro.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	
	@GetMapping
	public List<Pessoa>listar(){
	   return  pessoaRepository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa>criar(@RequestBody Pessoa pessoa, HttpServletResponse response){
		
		Pessoa pessoSalva = pessoaRepository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoSalva.getCodigo()).toUri();
		
		response.setHeader("Location",uri.toASCIIString());
		return ResponseEntity.created(uri).body(pessoSalva);
				
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa>buscarPessoa(@PathVariable  Long codigo){
		
		return pessoaRepository.findById(codigo).
				 map(pessoa -> ResponseEntity.ok(pessoa))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	

}
