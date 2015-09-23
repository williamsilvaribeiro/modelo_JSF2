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
import br.com.reiki.integracao.dao.CorVidroDao;
import br.com.reiki.integracao.entity.CorVidro;

@Service("corVidroService")
public class CorVidroServiceImpl implements CorVidroService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(CorVidroServiceImpl.class);

	@Autowired
	private CorVidroDao corVidroDao;

	public List<CorVidro> consultarCorVidro(CorVidro corVidro) {
		return corVidroDao.recuperarPorDescricao(corVidro);
	}

	public CorVidro carregarCorVidro(Integer id) {
		return (CorVidro) corVidroDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public CorVidro gravarCorVidro(CorVidro corVidroCadastro) throws ReikiException {
		try {
			if (corVidroCadastro.getId() == null) {
				corVidroCadastro = (CorVidro) corVidroDao.persistir(corVidroCadastro);
			} else {
				corVidroCadastro = (CorVidro) corVidroDao.atualizar(corVidroCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarCorVidro", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return corVidroCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarCorVidro(Integer id) throws ReikiException {
		try {
			corVidroDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarCorVidro", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<CorVidro> recuperaTodas() {
		return corVidroDao.recuperarTodas();
	}
	
}
