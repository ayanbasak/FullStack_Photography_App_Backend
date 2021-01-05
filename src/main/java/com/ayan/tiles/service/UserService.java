package com.ayan.tiles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayan.tiles.entity.User;
import com.ayan.tiles.exception.EntityIdException;
import com.ayan.tiles.exception.UsernameAlreadyExistsException;
import com.ayan.tiles.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/*
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}   */

	
	public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }
	public User findByEmail(String email) {
		return userRepository.findByUsername(email);

	}
	
	/* custom code */


	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long theId) {
		Optional<User> result = userRepository.findById(theId);
		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
		} else {
			throw new EntityIdException("Cannot find User: User Id " + theId + " as it doesn't exist");
		}
		return theUser;
	}

	public User save(User theUser) {
		userRepository.save(theUser);
		return theUser;
	}

	public void deleteById(Long theId) {
		findById(theId);
		userRepository.deleteById(theId);
	}

}