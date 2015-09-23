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
import br.com.reiki.integracao.constant.StatusPredefinidoEnum;
import br.com.reiki.integracao.dao.FuncaoDao;
import br.com.reiki.integracao.entity.Funcao;

@Service("funcaoService")
public class FuncaoServiceImpl implements FuncaoService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(FuncaoServiceImpl.class);

	@Autowired
	private FuncaoDao funcaoDao;

	public List<Funcao> consultarFuncao(Funcao funcao) {
		return funcaoDao.recuperarPorDescricao(funcao);
	}

	public Funcao carregarFuncao(Integer id) {
		return (Funcao) funcaoDao.carregar(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Funcao gravarFuncao(Funcao funcaoCadastro) throws ReikiException {
		try {
			funcaoCadastro.setStatusPredefinido(StatusPredefinidoEnum.NAO.getCodigo());
			if (funcaoCadastro.getId() == null) {
				funcaoCadastro = (Funcao) funcaoDao.persistir(funcaoCadastro);
			} else {
				funcaoCadastro = (Funcao) funcaoDao.atualizar(funcaoCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarFuncao", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return funcaoCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarFuncao(Integer id) throws ReikiException {
		try {
			funcaoDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarFuncao", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<Funcao> recuperaTodas() {
		return funcaoDao.recuperarTodas();
	}
	
}
