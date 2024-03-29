package com.codingdojo.bookclub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table (name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
    @Size(min=3, max=30, message="First name must be more than 2 Characters")
	private String name;
	
	
	@NotEmpty
    @Email(message="Please enter a valid email!")
	private String email;

	@NotEmpty
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty
	@Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	 @Column(updatable=false)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date createdAt;
	    
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date updatedAt;
	
	// Need to add one to many with plants OR thing that is many
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	 private List<Book> books;
	
	
	 
	 public User() {
		
	}
	
	
	
	public User(Long id,
			@NotEmpty @Size(min = 3, max = 30, message = "First name must be more than 2 Characters") String name,
			@NotEmpty @Email(message = "Please enter a valid email!") String email,
			@NotEmpty @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotEmpty @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirm,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public User(@NotEmpty @Size(min = 3, max = 30, message = "First name must be more than 2 Characters") String name,
			@NotEmpty @Email(message = "Please enter a valid email!") String email,
			@NotEmpty @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotEmpty @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirm) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	    }
	
}
