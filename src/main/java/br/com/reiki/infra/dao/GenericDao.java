package br.com.reiki.infra.dao;

public interface GenericDao<T> {

	public T carregar(Object id);

	public T persistir(T t);

	public T atualizar(T t);

	public void deletar(Object id);

}
