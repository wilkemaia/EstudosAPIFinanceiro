package com.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
