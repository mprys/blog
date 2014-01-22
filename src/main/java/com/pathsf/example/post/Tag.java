package com.pathsf.example.post;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pathsf.example.BaseEntity;

@Entity
@Table(name="Tags")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Tag.class)
public class Tag extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 2895109831531914813L;

	@Column(name="tagname")
	private String tagName;
	
	@ManyToMany(mappedBy="tags")
	private Set<Post> posts = new HashSet<Post>();

	public Tag(){
		
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}
