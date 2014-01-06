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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((roleDefinition == null) ? 0 : roleDefinition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleDefinition == null) {
			if (other.roleDefinition != null)
				return false;
		} else if (!roleDefinition.equals(other.roleDefinition))
			return false;
		return true;
	}
}
