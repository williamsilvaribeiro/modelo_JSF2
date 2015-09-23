package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Vidro;

public interface VidroDao extends GenericDao<Vidro> {

	public List<Vidro> consultarVidro(Vidro vidro);

	public List<Vidro> recuperarVidroCompleto(Integer id);

}
