package com.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

	

}
