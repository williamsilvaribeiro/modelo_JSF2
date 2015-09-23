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
import br.com.reiki.integracao.dao.BancoDao;
import br.com.reiki.integracao.entity.Banco;

@Service("bancoService")
public class BancoServiceImpl implements BancoService, Serializable {

	private static final long serialVersionUID = 6561295681179217783L;

	private static final Logger LOGGER = Logger.getLogger(BancoServiceImpl.class);

	@Autowired
	private BancoDao bancoDao;

	public List<Banco> consultarBanco(Banco banco) {
		return bancoDao.recuperarPorNomeOuNumero(banco);
	}

	public Banco carregarBanco(Integer id) {
		return (Banco) bancoDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Banco gravarBanco(Banco banco) throws ReikiException {
		try {
			if (banco.getId() == null) {
				banco = (Banco) bancoDao.persistir(banco);
			} else {
				banco = (Banco) bancoDao.atualizar(banco);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarBanco", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return banco;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarBanco(Integer id) throws ReikiException {
		try {
			bancoDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarBanco", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<Banco> recuperaTodos() {
		return bancoDao.recuperarTodos();
	}

}
