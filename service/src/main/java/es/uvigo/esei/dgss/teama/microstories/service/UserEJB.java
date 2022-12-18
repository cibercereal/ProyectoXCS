package es.uvigo.esei.dgss.teama.microstories.service;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.User;

@Stateless
@PermitAll
public class UserEJB {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private Principal principal;

	/**
	 * Returns the current user entity.
	 * 
	 * @return the entity with the information of the current user.
	 */
	public User getCurrentUser() {
		return this.em.find(User.class, this.principal.getName());
	}
	
	
	public boolean validateUser(String login, String password) {
		User user = em.find(User.class, login);
		
		return user.getPassword().equals(password);
	}

}
