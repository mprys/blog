package com.pathsf.example.account;

import javax.persistence.*;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pathsf.example.post.Post;

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
			
//			PersistenceUnitUtil util = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
//			
//			util.isLoaded(acc);
//			util.isLoaded(acc, "posts");
//			
//			Hibernate.initialize(acc.getPosts());
//			
//			util.isLoaded(acc, "posts");
//
//			for (Post post : acc.getPosts()) {
//				post.getId();
//			}
			
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
