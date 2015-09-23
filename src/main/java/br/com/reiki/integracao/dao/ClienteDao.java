package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Cliente;

public interface ClienteDao extends GenericDao<Cliente> {

	public List<Cliente> consultarCliente(Cliente cliente);

	public List<Cliente> recuperarClienteCompleto(Integer id);

}
