package com.pathsf.example.account;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.pathsf.example.BaseEntity;
import com.pathsf.example.post.Post;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@NamedQuery(name = Account.FIND_BY_EMAIL, query = "select a from Account a where a.email = :email")
public class Account extends BaseEntity implements java.io.Serializable {

	public static final String FIND_BY_EMAIL = "Account.findByEmail";

	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	private String password;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="account_id", referencedColumnName="id")
	private Set<Role> roles;
	
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JsonManagedReference
	private Set<Post> posts;
	
    protected Account() {

	}
	
	public Account(String email, String password, Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}
