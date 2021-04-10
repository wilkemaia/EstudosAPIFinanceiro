package com.financeiro.repository.lancamento;

import java.util.List;

import com.financeiro.model.Lancamento;
import com.financeiro.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
