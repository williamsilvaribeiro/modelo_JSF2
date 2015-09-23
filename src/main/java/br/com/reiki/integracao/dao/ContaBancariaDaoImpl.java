package br.com.reiki.integracao.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.ContaBancaria;

@Repository("contaBancariaDao")
public class ContaBancariaDaoImpl extends GenericDaoImpl<ContaBancaria> implements ContaBancariaDao, Serializable {

	private static final long serialVersionUID = 1L;
	
}
