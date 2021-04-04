package com.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
