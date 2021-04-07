package com.financeiro.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.event.RecursoCriadoEvent;
import com.financeiro.model.Lancamento;
import com.financeiro.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	LancamentoService lancamentoService;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Lancamento> listarLancamento() {
		return lancamentoService.listarLancamento();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){
	  Lancamento lancamento =  lancamentoService.buscarLancamento(codigo);
	  return  lancamento !=null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Lancamento>gerarLancamento(@Valid @RequestBody Lancamento lancamento,HttpServletResponse response){
		
		Lancamento lancamentoSalvo =  lancamentoService.gerarLanc(lancamento);
		
		publisher.publishEvent(new  RecursoCriadoEvent(this,response,lancamentoSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
		
	}

}
