package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.constant.FuncaoEnum;
import br.com.reiki.integracao.constant.RegistroAtivoEnum;
import br.com.reiki.integracao.entity.Funcionario;

@Repository("funcionarioDao")
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario> implements FuncionarioDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Funcionario> consultarFuncionario(Funcionario funcionario) {
		String igFuncao = Util.defineSeIgnorar(funcionario.getFuncao().getId());
		String igStatusAtivo = Util.defineSeIgnorar(funcionario.getStatusAtivo());
		String igTipoPessoa = Util.defineSeIgnorar(funcionario.getTipoPessoa());
		Query query = getEntityManager().createQuery("select f from Funcionario f "
				+ "left join fetch f.funcao fun "
				+ "where f.nome like :nome "
				+ "and ( f.funcao.id = :idFuncao or 'ignora' = :igFuncao ) "
				+ "and ( f.statusAtivo = :statusAtivo or 'ignora' = :igStatusAtivo ) "
				+ "and ( f.tipoPessoa = :tipoPessoa or 'ignora' = :igTipoPessoa ) "
				+ "order by f.nome ");
		query.setParameter("nome", "%" + funcionario.getNome() + "%");
		query.setParameter("idFuncao", funcionario.getFuncao().getId());
		query.setParameter("igFuncao", igFuncao);
		query.setParameter("statusAtivo", funcionario.getStatusAtivo());
		query.setParameter("igStatusAtivo", igStatusAtivo);
		query.setParameter("tipoPessoa", funcionario.getTipoPessoa());
		query.setParameter("igTipoPessoa", igTipoPessoa);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarFuncionarioCompleto(Integer id) {
		Query query = getEntityManager().createQuery("select f from Funcionario f "
				+ "left join fetch f.funcao fun "
				+ "left join fetch f.contaBancaria cb "
				+ "left join fetch cb.banco b "
				+ "where f.id = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperaTodosVendedoresAtivos() {
		Query query = getEntityManager().createQuery("select f from Funcionario f "
				+ "left join fetch f.funcao fun "
				+ "where fun.id = :idFuncao "
				+ "and f.statusAtivo = :statusAtivo ");
		query.setParameter("idFuncao", FuncaoEnum.VENDEDOR.getCodigo());
		query.setParameter("statusAtivo", RegistroAtivoEnum.SIM.getCodigo());
		return query.getResultList();
	}

}
