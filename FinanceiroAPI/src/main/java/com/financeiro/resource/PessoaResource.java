package com.financeiro.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financeiro.model.Pessoa;
import com.financeiro.repository.PessoaRepository;
import com.financeiro.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	
	@GetMapping
	public List<Pessoa>listar(){
	   return  pessoaRepository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa>criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		
		Pessoa pessoSalva = pessoaRepository.save(pessoa);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoSalva.getCodigo()).toUri();
		
		response.setHeader("Location",uri.toASCIIString());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoSalva);
				
	}
		
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa>buscarPeloCodigo(@PathVariable Long codigo){
		return pessoaRepository.findById(codigo).map(pessoa -> ResponseEntity.ok(pessoa)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
	
		 this.pessoaRepository.deleteById(codigo);
	}
	
   @PutMapping("/{codigo}")
   public ResponseEntity<Pessoa>atualizar(@PathVariable Long codigo ,@Valid @RequestBody Pessoa pessoa){
	   
	   Pessoa pessoaSalva = pessoaService.atulizar(codigo, pessoa);
	   
	   return ResponseEntity.ok(pessoaSalva);
	   
   }
   
   @PutMapping("/{codigo}/ativo")
   public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo ) {
	    pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
   }
   
   
   
	
	

}
