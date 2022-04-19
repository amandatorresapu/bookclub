package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	
//	Find all ---------------------------------- c/p (edit out THINGS) 	
	public List<Book> allBooks(){
		return bookRepository.findAll();
	}
	
//	find one ---------------------------------- c/p (edit out THINGS) 
	public Book findOne(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			return null;
		}

		
	}
	
//	create-delete-update* ---------------------------------- c/p (edit out THINGS) 
	public Book create(Book plant) {
		return bookRepository.save(plant);
	}
	
	public Book update(Book plant) {
		return bookRepository.save(plant);
	}
	
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	

}
