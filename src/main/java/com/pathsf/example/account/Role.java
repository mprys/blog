package com.pathsf.example.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pathsf.example.BaseEntity;

@Table(name="roles")
@Entity
public class Role extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 5040798386499987559L;
	
	@Column(name="role_definition")
	private String roleDefinition;

	public Role(){
		
	}
	
	public Role(String roleDef){
		this.roleDefinition = roleDef;
	}
	
	public String getRoleDefinition() {
		return roleDefinition;
	}

	public void setRoleDefinition(String roleDefinition) {
		this.roleDefinition = roleDefinition;
	}
}
