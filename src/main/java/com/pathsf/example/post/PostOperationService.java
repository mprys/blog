package com.pathsf.example.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

		Tag tag = new Tag();
		tag.setTagName("Car");
		post.getTags().add(tag);

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
	
	@Transactional(readOnly=true)
	public List<Post> readAllPost(String email){
		Account acc = accountRepo.findByEmail(email);
		
		return postRepo.readAllPosts(acc.getId());
	}
	
	@Transactional(readOnly=false)
	public Post updatePost(Long id, Post newPost){
		return postRepo.updatePost(id, newPost);
	}
	
	@Transactional(readOnly=false)
	public void deletePost(Long id){
		postRepo.deletePost(id);
	}
	
	@Transactional(readOnly=false)
	public Comment makeCommentToPost(Long postId, String userName, Comment comment){
		
		Account acc = accountRepo.findByEmail(userName);
		Assert.notNull(acc, "User info not found, please login first!");
		Post post = postRepo.readPostById(postId);
		comment.setOwnerName(userName);
		comment.setPost(post);
		
		return postRepo.insertComment(comment);
	}
}
