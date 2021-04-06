package com.financeiro.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.model.Pessoa;
import com.financeiro.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepositoty;
	
	public Pessoa atulizar(Long codigo, Pessoa pessoa) {
		
		 Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);		
		 BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		 return pessoaRepositoty.save(pessoaSalva);
	}
	
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		
		Pessoa pessoaSalva= buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepositoty.save(pessoaSalva);
	}
	

	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepositoty.findById(codigo)
				 .orElseThrow(()-> new EmptyResultDataAccessException(1));
		
		return pessoaSalva;
	}
	
	
}
