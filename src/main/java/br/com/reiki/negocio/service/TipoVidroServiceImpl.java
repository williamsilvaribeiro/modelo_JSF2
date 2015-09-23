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
import br.com.reiki.integracao.dao.TipoVidroDao;
import br.com.reiki.integracao.entity.TipoVidro;

@Service("tipoVidroService")
public class TipoVidroServiceImpl implements TipoVidroService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(TipoVidroServiceImpl.class);

	@Autowired
	private TipoVidroDao tipoVidroDao;

	public List<TipoVidro> consultarTipoVidro(TipoVidro tipoVidro) {
		return tipoVidroDao.recuperarPorDescricao(tipoVidro);
	}

	public TipoVidro carregarTipoVidro(Integer id) {
		return (TipoVidro) tipoVidroDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public TipoVidro gravarTipoVidro(TipoVidro tipoVidroCadastro) throws ReikiException {
		try {
			if (tipoVidroCadastro.getId() == null) {
				tipoVidroCadastro = (TipoVidro) tipoVidroDao.persistir(tipoVidroCadastro);
			} else {
				tipoVidroCadastro = (TipoVidro) tipoVidroDao.atualizar(tipoVidroCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarTipoVidro", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return tipoVidroCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarTipoVidro(Integer id) throws ReikiException {
		try {
			tipoVidroDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarTipoVidro", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<TipoVidro> recuperaTodos() {
		return tipoVidroDao.recuperarTodos();
	}
	
}
