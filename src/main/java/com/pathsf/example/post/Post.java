package com.pathsf.example.post;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pathsf.example.BaseEntity;
import com.pathsf.example.account.Account;


@Entity
@Table(name="Posts")
@NamedQueries({
	@NamedQuery(name="Post.findAllByAuthorId", query="FROM Post where author.id = ?")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Post.class)
public class Post extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -7256583660809016167L;
	
	@Column(name="title")
	String title;
	
	@Column(name="content", columnDefinition="TEXT")
	String content;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="Posts_Tags", 
			   joinColumns={@JoinColumn(name = "post_id")}, 
			   inverseJoinColumns={@JoinColumn(name="tag_id")})
	Set<Tag> tags = new HashSet<Tag>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy="post")
	Set<Comment> comments;
	
	@Column(name="published")
	Boolean published;

	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private Account author;

	public Post(){
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}
}
