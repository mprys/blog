package com.pathsf.example.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pathsf.example.account.Account;
import com.pathsf.example.account.AccountRepository;

@Service
public class PostOperationService {

	@Autowired
	PostOperationRepository postRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Transactional(readOnly=false)
	public Post createPost(Post post, String email){
		Account acc = accountRepo.findByEmail(email);
		post.setAuthor(acc);
		return postRepo.createPost(post);
	}
	
	@Transactional(readOnly=false)
	public Post publishPost(Long id, String email){
		
		Account acc = accountRepo.findByEmail(email);
		Post post = postRepo.readPostById(id);
		post.setPublished(Boolean.TRUE);
		post.setAuthor(acc);
		return postRepo.publishPost(post);
	}
	
	@Transactional(readOnly=true)
	public Post readPostDetailsById(Long id){
		return postRepo.readPostById(id);
	}
}
