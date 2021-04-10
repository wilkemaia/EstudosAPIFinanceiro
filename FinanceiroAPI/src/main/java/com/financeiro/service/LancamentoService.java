package com.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.model.Lancamento;
import com.financeiro.model.Pessoa;
import com.financeiro.repository.LancamentoRepository;
import com.financeiro.repository.PessoaRepository;
import com.financeiro.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	 private PessoaRepository pessoaRepository;
	
	public Lancamento buscarLancamento(Long codigo) {
		Lancamento lancamentoPesq =  lancamentoRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		
		return  lancamentoPesq;
	}
	
	
	public List<Lancamento>listarLancamento(){
		
		return lancamentoRepository.findAll();
	}
	
	
	public Lancamento gerarLanc(Lancamento lancamento) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		if (!pessoa.isPresent() || pessoa.get().isInativo()) {
			
			throw new PessoaInexistenteOuInativaException();
		}
		
		return  lancamentoRepository.save(lancamento);
	}
	
}
