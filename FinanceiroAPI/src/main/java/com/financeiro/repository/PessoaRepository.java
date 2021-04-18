package com.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.financeiro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

 	
	@Query("SELECT obj FROM Pessoa obj WHERE LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%')) and ativo=false")
	Page<Pessoa> searchByName(String nome, Pageable pageable);
	Page<Pessoa>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	@Query("SELECT obj FROM Pessoa obj WHERE obj.salario >= :minSalary AND obj.salario <= :maxSalary")
	Page<Pessoa> findBySalarioBetween(Double minSalary, Double maxSalary, Pageable pageable);

}
