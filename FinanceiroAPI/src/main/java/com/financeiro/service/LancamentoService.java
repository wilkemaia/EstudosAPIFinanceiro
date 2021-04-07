package com.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.model.Lancamento;
import com.financeiro.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	LancamentoRepository lancamentoRepository;
	
	public Lancamento buscarLancamento(Long codigo) {
		Lancamento lancamentoPesq =  lancamentoRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		
		return  lancamentoPesq;
	}
	
	
	public List<Lancamento>listarLancamento(){
		
		return lancamentoRepository.findAll();
	}
	
	
	public Lancamento gerarLanc(Lancamento lancamento) {
		
		return  lancamentoRepository.save(lancamento);
	}
	
}
