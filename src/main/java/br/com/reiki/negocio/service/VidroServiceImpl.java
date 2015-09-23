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
import br.com.reiki.integracao.dao.ContaBancariaDao;
import br.com.reiki.integracao.dao.VidroDao;
import br.com.reiki.integracao.entity.Vidro;

@Service("vidroService")
public class VidroServiceImpl implements VidroService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(VidroServiceImpl.class);

	@Autowired
	private VidroDao vidroDao;

	@Autowired
	private ContaBancariaDao contaBancariaDao;

	public List<Vidro> consultarVidro(Vidro vidro) {
		return vidroDao.consultarVidro(vidro);
	}

	public Vidro carregarVidro(Integer id) {
		return (Vidro) vidroDao.carregar(id);
	}

	public Vidro carregarVidroCompleto(Integer id) {
		Vidro vidro = null;
		List<Vidro> resultado = vidroDao.recuperarVidroCompleto(id);
		if (!resultado.isEmpty()) {
			vidro = resultado.get(0);
		}
		return vidro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Vidro gravarVidro(Vidro vidroCadastro) throws ReikiException {
		try {
			if (vidroCadastro.getId() == null) {
				vidroCadastro = (Vidro) vidroDao.persistir(vidroCadastro);
			} else {
				vidroCadastro = (Vidro) vidroDao.atualizar(vidroCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarVidro", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return vidroCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarVidro(Integer id) throws ReikiException {
		try {
			vidroDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarVidro", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

}
