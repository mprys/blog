package com.pathsf.example.post;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathsf.example.support.web.Message;
import com.pathsf.example.support.web.Message.Type;
import com.pathsf.example.support.web.MessageHelper;

@Controller
public class PostOperationController {

	@Autowired
	PostOperationService postService;
	
	@RequestMapping(value="/post/create", method=RequestMethod.GET)
	public String setup(Model model){
		
		model.addAttribute("Post", new Post());
		
		return "/post/editor";
	}
	
	@RequestMapping(value="/post/create", method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public Post createPost(@RequestBody Post post, Principal principal){

		Post created = postService.createPost(post, principal.getName());
		return created;
	}
	
	@RequestMapping(value="/post/publish/{id}", method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public Post publishPost(@PathVariable Long id, Principal principal){
		Assert.notNull(id, "Id can not be null !");
		Assert.notNull(principal, "Principal can not be null !");
		Post p = postService.publishPost(id, principal.getName());
		return p;
	}
	
	@RequestMapping(value="/post/details/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public Post displayPostDetails(@PathVariable Long id, Principal principal, Model model){
		
		Post p = postService.readPostDetailsById(id);
		
		return p;
	}
	
	@RequestMapping(value="/post/all", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public List<Post> displayAllPosts(Principal principal, Model model){

		return postService.readAllPost(principal.getName());
	}
	
	@RequestMapping(value="/post/list", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String displayAll(Principal principal, Model model){
		
		return "/post/publishedposts";
	}
	
	@RequestMapping(value="/post/edit/{id}", method=RequestMethod.GET)
	public String setupEditMode(@PathVariable Long id, Principal principal, Model model){
		
		Post p = postService.readPostDetailsById(id);
		model.addAttribute("Post", p);

		return "post/postdetails";
	}
	
	@RequestMapping(value="/post/update/{id}", method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public Post updatePost(@PathVariable Long id, @RequestBody Post post, Principal principal){
		
		return postService.updatePost(id, post);
	}

	@RequestMapping(value="/post/delete/{id}", method=RequestMethod.DELETE, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public void deletePost(@PathVariable Long id, Principal principal){
		
		postService.deletePost(id);
	}
	
	@RequestMapping(value="/post/{id}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String showPublishedPost(@PathVariable Long id, Principal principal, Model model, RedirectAttributes ra){
		
		model.addAttribute("Post", postService.readPostDetailsById(id));
		//model.addAttribute("message", new Message("Basarili", Type.SUCCESS, "Basarili"));
		
		return "post/publishedpost";
	}
	
	@RequestMapping(value="/post/{postId}/{userName}/comment", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public Comment makeCommentToPost(@PathVariable Long postId, @PathVariable String userName, @RequestBody Comment comment){
		
		return postService.makeCommentToPost(postId, userName, comment); 	
	}
}
