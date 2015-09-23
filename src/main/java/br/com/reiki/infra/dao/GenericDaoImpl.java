package br.com.reiki.infra.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private final Class<T> type;

	@PersistenceContext
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public T carregar(final Object id) {
		return (T) getEntityManager().find(type, id);
	}

	public T persistir(final T t) {
		getEntityManager().persist(t);
		getEntityManager().flush();
		return t;
	}

	public T atualizar(final T t) {
		getEntityManager().merge(t);
		getEntityManager().flush();
		return t;
	}

	public void deletar(final Object id) {
		getEntityManager().remove(getEntityManager().getReference(type, id));
		getEntityManager().flush();
	}

}
