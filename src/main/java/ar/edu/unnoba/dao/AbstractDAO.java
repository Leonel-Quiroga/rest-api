package ar.edu.unnoba.dao;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class AbstractDAO<T> {
	
	private final Class<T> typeParameter;
	protected EntityManager em = Persistence.createEntityManagerFactory("rest-api").createEntityManager();

	public AbstractDAO(Class<T> type) {
		this.typeParameter = type;
	}

	public void create(T t) throws EJBException {
		em.persist(t);
	}

	public void update(T t) throws EJBException {
		em.merge(t);
	}

	public void delete(T t) throws EJBException {
		em.remove(em.contains(t) ? t : em.merge(t));
	}

	public void flush() {
		em.flush();
	}

	public void refresh(T t) {
		em.refresh(t);
	}

	public T find(Long id) {
		return em.find(typeParameter, id);
	}
}
