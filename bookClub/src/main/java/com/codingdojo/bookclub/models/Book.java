package com.codingdojo.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table (name="books")
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
    @Size(min=2, message="title must be more than 3 Characters")
	private String title;
	@NotEmpty
	@Size(min=2, message="Author must be more than 2 Characters")
	private String author;
	@NotEmpty
	@Size(min=5, message="Description must be more than 5 Characters")
	private String description;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	    
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Book() {
		super();
	}
	
	
	public Book(@NotEmpty @Size(min = 2, message = "title must be more than 3 Characters") String title,
			@NotEmpty @Size(min = 2, message = "Author must be more than 2 Characters") String author,
			@NotEmpty @Size(min = 5, message = "Description must be more than 5 Characters") String description,
			User user) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.user = user;
	}


	public Book(Long id, @NotEmpty @Size(min = 2, message = "title must be more than 3 Characters") String title,
			@NotEmpty @Size(min = 2, message = "Author must be more than 2 Characters") String author,
			@NotEmpty @Size(min = 5, message = "Description must be more than 5 Characters") String description,
			Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	    }
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	
	
	
	
	
	

}
