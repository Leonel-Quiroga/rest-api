package ar.edu.unnoba.dao;


import javax.persistence.Query;

import ar.edu.unnoba.domain.User;
import ar.edu.unnoba.exceptions.EntityNotFoundException;

public class UserDAO extends AbstractDAO<User> {

	public UserDAO() {
		super(User.class);
	}


	public User getUser(String email) throws EntityNotFoundException {
		User result = new User();
		
		Query query = em.createNamedQuery("User.findByEmailAndPassword");
		query.setParameter("email", email);
		result = (User) query.getSingleResult();
		if (result != null) {
			return result; 
		}else {
			throw new EntityNotFoundException("No se encontro el usuario");
		}		
	}
	
	public User getUserById(Integer id) throws EntityNotFoundException {
		User result = new User();
		
		Query query = em.createNamedQuery("User.findUserById");
		query.setParameter("id", id);
		result = (User) query.getSingleResult();
		if (result != null) {
			return result; 
		}else {
			throw new EntityNotFoundException("No se encontro el usuario");
		}		
	}
	
}
