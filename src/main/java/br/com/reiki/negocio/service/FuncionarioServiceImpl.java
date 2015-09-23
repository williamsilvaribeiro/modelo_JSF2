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
import br.com.reiki.integracao.constant.FuncaoEnum;
import br.com.reiki.integracao.constant.TipoPessoaEnum;
import br.com.reiki.integracao.dao.ContaBancariaDao;
import br.com.reiki.integracao.dao.FuncionarioDao;
import br.com.reiki.integracao.entity.ContaBancaria;
import br.com.reiki.integracao.entity.Funcionario;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService, Serializable {

	private static final long serialVersionUID = 2887147027231739158L;

	private static final Logger LOGGER = Logger.getLogger(FuncionarioServiceImpl.class);

	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Autowired
	private ContaBancariaDao contaBancariaDao;
	
	public List<Funcionario> consultarFuncionario(Funcionario funcionario) {
		return funcionarioDao.consultarFuncionario(funcionario);
	}

	public Funcionario carregarFuncionario(Integer id) {
		return (Funcionario) funcionarioDao.carregar(id);
	}
	
	public Funcionario carregarFuncionarioCompleto(Integer id) {
		Funcionario funcionario = null;
		List<Funcionario> resultado = funcionarioDao.recuperarFuncionarioCompleto(id);
		if(!resultado.isEmpty()){
			funcionario = resultado.get(0);
		}
		return funcionario;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Funcionario gravarFuncionario(Funcionario funcionarioCadastro) throws ReikiException {
		try {
			ContaBancaria contaBancaria = funcionarioCadastro.getContaBancaria();
			if (contaBancaria.getId() == null) {
				contaBancaria = contaBancariaDao.persistir(contaBancaria);
			} else {
				contaBancaria = contaBancariaDao.atualizar(contaBancaria);
			}
			funcionarioCadastro.setContaBancaria(contaBancaria);
			if(funcionarioCadastro.getTipoPessoa().equals(TipoPessoaEnum.PESSOA_FISICA.getCodigo())){
				funcionarioCadastro.setCnpj(null);
				funcionarioCadastro.setInscricaoEstadual(null);
			}
			if(funcionarioCadastro.getTipoPessoa().equals(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo())){
				funcionarioCadastro.setCpf(null);
				funcionarioCadastro.setRg(null);
			}
			if(!funcionarioCadastro.getFuncao().getId().equals(FuncaoEnum.VENDEDOR.getCodigo())){
				funcionarioCadastro.setPercentualComissaoVenda(null);
			}
			if (funcionarioCadastro.getId() == null) {
				funcionarioCadastro = (Funcionario) funcionarioDao.persistir(funcionarioCadastro);
			} else {
				funcionarioCadastro = (Funcionario) funcionarioDao.atualizar(funcionarioCadastro);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarFuncionario", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return funcionarioCadastro;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarFuncionario(Integer id) throws ReikiException {
		try {
			funcionarioDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarFuncionario", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	public List<Funcionario> recuperaTodosVendedoresAtivos() {
		return funcionarioDao.recuperaTodosVendedoresAtivos();
	}
	
}
