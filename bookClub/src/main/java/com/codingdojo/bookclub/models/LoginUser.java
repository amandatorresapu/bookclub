package com.codingdojo.bookclub.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//No entity not saving to DB
public class LoginUser {
	
	@NotEmpty
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;


	public LoginUser() {
		super();
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
    
    
    

}
