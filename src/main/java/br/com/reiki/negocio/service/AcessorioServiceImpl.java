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
import br.com.reiki.integracao.dao.AcessorioDao;
import br.com.reiki.integracao.entity.Acessorio;

@Service("acessorioService")
public class AcessorioServiceImpl implements AcessorioService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(AcessorioServiceImpl.class);

	@Autowired
	private AcessorioDao acessorioDao;

	public List<Acessorio> consultarAcessorio(Acessorio acessorio) {
		return acessorioDao.recuperarPorDescricao(acessorio);
	}

	public Acessorio carregarAcessorio(Integer id) {
		return (Acessorio) acessorioDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Acessorio gravarAcessorio(Acessorio acessorioCadastro) throws ReikiException {
		try {
			if (acessorioCadastro.getId() == null) {
				acessorioCadastro = (Acessorio) acessorioDao.persistir(acessorioCadastro);
			} else {
				acessorioCadastro = (Acessorio) acessorioDao.atualizar(acessorioCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarAcessorio", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return acessorioCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarAcessorio(Integer id) throws ReikiException {
		try {
			acessorioDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarAcessorio", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<Acessorio> recuperaTodos() {
		return acessorioDao.recuperarTodos();
	}
	
}
