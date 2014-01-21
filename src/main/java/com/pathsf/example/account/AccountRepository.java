package com.pathsf.example.account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		entityManager.persist(account);
		return account;
	}
	
	public Account findByEmail(String email) {
		try {
			Account acc = entityManager.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
			.setParameter("email", email)
			.getSingleResult();
			
			//entityManager.detach(acc);
			return acc;
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	public Account findByAccountId(Long id){
		
		return entityManager.find(Account.class, id);
	}
	
	public Role addRole(Role role){
		
		entityManager.persist(role);	
		return role;
	}

	
}
