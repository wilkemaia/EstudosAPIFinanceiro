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
		
		 Pessoa pessoaSalva = pessoaRepositoty.findById(codigo)
				 .orElseThrow(()-> new EmptyResultDataAccessException(1));
		 BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");		
		 
		 return pessoaRepositoty.save(pessoaSalva);
	}
}
