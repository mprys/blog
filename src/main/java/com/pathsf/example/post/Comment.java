package com.pathsf.example.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pathsf.example.BaseEntity;

@Entity
@Table(name="Comments")
public class Comment extends BaseEntity implements Serializable{

	@Transient
	private static final long serialVersionUID = -165368359132379816L;
	
	@Column(name="ownername")
	private String ownerName;
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	private Post post;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
