package br.com.reiki.negocio.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.constant.TipoPessoaEnum;
import br.com.reiki.integracao.dao.ClienteDao;
import br.com.reiki.integracao.dao.ContaBancariaDao;
import br.com.reiki.integracao.entity.Cliente;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private ContaBancariaDao contaBancariaDao;

	public List<Cliente> consultarCliente(Cliente cliente) {
		return clienteDao.consultarCliente(cliente);
	}

	public Cliente carregarCliente(Integer id) {
		return (Cliente) clienteDao.carregar(id);
	}

	public Cliente carregarClienteCompleto(Integer id) {
		Cliente cliente = null;
		List<Cliente> resultado = clienteDao.recuperarClienteCompleto(id);
		if (!resultado.isEmpty()) {
			cliente = resultado.get(0);
		}
		return cliente;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Cliente gravarCliente(Cliente clienteCadastro) throws ReikiException {
		try {
			if (clienteCadastro.getTipoPessoa().equals(TipoPessoaEnum.PESSOA_FISICA.getCodigo())) {
				clienteCadastro.setCnpj(null);
				clienteCadastro.setInscricaoEstadual(null);
			}
			if (clienteCadastro.getTipoPessoa().equals(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo())) {
				clienteCadastro.setCpf(null);
				clienteCadastro.setRg(null);
			}
			if (clienteCadastro.getId() == null) {
				clienteCadastro = (Cliente) clienteDao.persistir(clienteCadastro);
			} else {
				clienteCadastro = (Cliente) clienteDao.atualizar(clienteCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarCliente", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return clienteCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarCliente(Integer id) throws ReikiException {
		try {
			clienteDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarCliente", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

}
