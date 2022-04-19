package com.codingdojo.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;



@Controller
public class HomeController {
	
	private final UserService userService;
	private final BookService bookService;
	
	
	
	
	public HomeController(UserService userService, BookService bookService) {
		
		this.userService = userService;
		this.bookService = bookService;
	}


//	Login and Registration ---------------------c/p
	@GetMapping("/")
	public String index(Model model) {
	
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	
//	**-----New Post route to register************ c/p	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		userService.register(newUser, result);
		 
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";
	}
	
	
//	**-----New Post route to Login***************c/p
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin")LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("user_id", user.getId());
		return "redirect:/dashboard";
	}

	
//	Dashboard ----------------------------------
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("user_id") !=null) {
			//dont add below until you create the THINGS SERVICE page !!***
			model.addAttribute("allBooks", bookService.allBooks());
			model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
			
			return "dashboard.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	
//	Logout -------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
	
		return "redirect:/";
	}

	
//	Creating a book ---------------------------
	@GetMapping("/createBook")
	public String createBook(@ModelAttribute("book")Book book, Model model, HttpSession session) {
		model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
		return "createBook.jsp";
	}
	

	
//	***-----New Post route to create a new book***--- c/p change out THINGS GET html form form plant/book club
	@PostMapping("/makingBook")
	public String makingBook(@Valid @ModelAttribute("book")Book book, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
			return "createBook.jsp";
			
		}
		bookService.create(book);
		return "redirect:/dashboard";
	}
	
	
	
	
	

//	Viewing one book --------------------------
	@GetMapping("/oneBook/{id}")
	public String vewOneBook(@PathVariable("id")Long id, Model model, HttpSession session) {
	
		model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
		model.addAttribute("book", bookService.findOne(id));
		return "oneBook.jsp";
	}
	
	
	
//	Delete a book -----------------------------
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		bookService.delete(id);
		return "redirect:/dashboard";
	}
	

	
	
//	Edit a book -------------------------------
//	***Need to add ID's /<id...
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") Long id, @ModelAttribute("book")Book book,Model model, HttpSession session) {
		model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
		model.addAttribute("book", bookService.findOne(id));
		return "editBook.jsp";
	}
	
	
//	-----New Post route to edit book*********
//	***Need to add ID's /<id...
	@PutMapping("/editingBook/{id}")
	public String editingBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book")Book book,BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
			return "editBook.jsp";
		}
		
		bookService.update(book);
		return "redirect:/dashboard";

	}
	
	
	
	
	
	
	
}
