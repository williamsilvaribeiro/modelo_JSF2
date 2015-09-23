package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Cliente;

public interface ClienteService {

	public List<Cliente> consultarCliente(Cliente cliente);

	public Cliente carregarCliente(Integer id);

	public Cliente gravarCliente(Cliente clienteCadastro) throws ReikiException;

	public void deletarCliente(Integer id) throws ReikiException;

	public Cliente carregarClienteCompleto(Integer id);

}
