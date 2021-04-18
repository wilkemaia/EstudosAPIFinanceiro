package com.financeiro.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.financeiro.model.Lancamento;

import com.financeiro.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl  implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter,Pageable pageable ) {
		CriteriaBuilder builder = manager.getCriteriaBuilder(); 
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		Root<Lancamento> root =  criteria.from(Lancamento.class);
		
		//Criar as restrições
		Predicate[] predicates = criarRestricoes(lancamentoFilter,builder,root);
		criteria.where(predicates);
		
		
		TypedQuery<Lancamento>query = manager.createQuery(criteria);
		
		adcionarRestricoesPaginacao(query,pageable);
		
		
		return  new PageImpl<Lancamento>(query.getResultList(), pageable,total(lancamentoFilter));
		
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root =  criteria.from(Lancamento.class);
		
		Predicate[] predicates =  criarRestricoes(lancamentoFilter,builder,root);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adcionarRestricoesPaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
		
		int pageAtual =  pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegitroDaPagina =  pageAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegitroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(lancamentoFilter.getDescricao() !=null) {
			
			predicates.add(builder.like(
			      builder.lower(root.get("descricao")),"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
								
		}
		
		if(lancamentoFilter.getDataVencimentoDe() !=null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
		}
		
		if(lancamentoFilter.getDataVencimentoAte() !=null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
