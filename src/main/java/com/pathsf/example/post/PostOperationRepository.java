package com.pathsf.example.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
	public List<Post> readAllPosts(Long authorId){
	 	
		TypedQuery<Post> query = em.createNamedQuery("Post.findAllByAuthorId", Post.class);
		query.setParameter(1, authorId);
		return query.getResultList();
	}
}
