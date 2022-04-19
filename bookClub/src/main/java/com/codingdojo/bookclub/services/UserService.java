package com.codingdojo.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
//	Find one user
	public User oneUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
	}
	
//	****Method for Register
	public User register(User newUser, BindingResult result) {
		// Check to see if email is in DB-- if not produce error
		if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "Unique", "You better think of a new email, we already have this one");
		}
		//Check to see if password and confirm pass match - if not produce error
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "your password is bad and they have to match");
		}
		// check if there are any errors
		if(result.hasErrors()) {
			return null;
		} else {
			// If no errors - hash Password - set password - Save user in DB
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			return userRepository.save(newUser);
		}
	}
	
	
//	****Method for Login
	public User login(LoginUser newLogin, BindingResult result) {
		//check to see if error exist and return null if so
		if(result.hasErrors()) {
			return null;
		}
		//use optional to find the user by email
		Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());
		//check to see if the email is in the db- if not product error
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", "Unique", "we have no record of you creating an account with our buiness");
		}
		//if email is in DB we get the user
		User user = potentialUser.get();
		//Check the logging in user password with what we have in the DB based the email given
		//if these do not match we produce error
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())){
			result.rejectValue("password", "Are you trying to hack into someone account?");
			
		}
		//if we have any errors we return null
		if(result.hasErrors()) {
			return null;
		}else {
		//if everything is good then we return the user
			return user;
		}
	
	}
	
	

}
