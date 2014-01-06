package com.pathsf.example.post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PostOperationRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Post createPost(Post post){
		em.persist(post);
		return post;
	}
	
	public Post readPostById(Long id){
		return em.find(Post.class, id);
	}
	
	public Post publishPost(Post post){
		return em.merge(post);
	}
}
