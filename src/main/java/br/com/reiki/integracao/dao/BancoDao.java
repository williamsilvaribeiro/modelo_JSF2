package br.com.reiki.integracao.dao;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Banco;

import java.util.List;

public interface BancoDao extends GenericDao<Banco> {

    public List<Banco> recuperarPorNomeOuNumero(Banco banco);

	public List<Banco> recuperarTodos();

}
