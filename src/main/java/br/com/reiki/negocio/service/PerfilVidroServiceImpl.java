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
import br.com.reiki.integracao.dao.PerfilVidroDao;
import br.com.reiki.integracao.entity.PerfilVidro;

@Service("perfilVidroService")
public class PerfilVidroServiceImpl implements PerfilVidroService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(PerfilVidroServiceImpl.class);

	@Autowired
	private PerfilVidroDao perfilVidroDao;

	public List<PerfilVidro> consultarPerfilVidro(PerfilVidro perfilVidro) {
		return perfilVidroDao.recuperarPorDescricao(perfilVidro);
	}

	public PerfilVidro carregarPerfilVidro(Integer id) {
		return (PerfilVidro) perfilVidroDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public PerfilVidro gravarPerfilVidro(PerfilVidro perfilVidroCadastro) throws ReikiException {
		try {
			if (perfilVidroCadastro.getId() == null) {
				perfilVidroCadastro = (PerfilVidro) perfilVidroDao.persistir(perfilVidroCadastro);
			} else {
				perfilVidroCadastro = (PerfilVidro) perfilVidroDao.atualizar(perfilVidroCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarPerfilVidro", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return perfilVidroCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarPerfilVidro(Integer id) throws ReikiException {
		try {
			perfilVidroDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarPerfilVidro", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<PerfilVidro> recuperaTodos() {
		return perfilVidroDao.recuperarTodos();
	}
	
}
