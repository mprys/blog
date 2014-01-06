package com.pathsf.example.signup;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.pathsf.example.account.Account;
import com.pathsf.example.account.Role;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

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

	public Account createAccount(Set<Role> roles) {
        return new Account(getEmail(), getPassword(), roles);
	}
	
	public Set<Role> createRoles(String roleDesc){
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role(roleDesc));
		return roles;
	}
}
