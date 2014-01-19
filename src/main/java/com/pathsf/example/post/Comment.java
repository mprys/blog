package com.pathsf.example.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.pathsf.example.BaseEntity;
import com.pathsf.example.account.Account;

@Entity
@Table(name="Comments")
public class Comment extends BaseEntity implements Serializable{

	@Transient
	private static final long serialVersionUID = -165368359132379816L;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	@JsonBackReference
	private Account owner; 
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	@JsonBackReference
	private Post post;
	
	@Column(name="comment", columnDefinition="TEXT")
	private String comment;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}
}
