package com.company.retrospective.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.retrospective.model.Board;
import com.company.retrospective.model.User;
import com.company.retrospective.model.UserDetails;
import com.company.retrospective.repository.UserDetailsRepository;
import com.company.retrospective.repository.UserRepository;

@RestController
@RequestMapping(path = "v1/user")

public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@GetMapping(value = "/{uname}/{password}")
	public ResponseEntity<User> getUser(@PathVariable String uname, @PathVariable String password) {

		System.out.println("Get User Request for " + uname + " and password " + password);
		User user = userRepository.findByUserNameAndPassword(uname, password);
		System.out.println("Object found is " + user);
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<>(user, headers, HttpStatus.OK);
	}

	@PostMapping(path = "/register")
	@CrossOrigin(origins = "*")
	public ResponseEntity<User> addUser(@RequestBody User user) {

		System.out.println("Add User Request for " + user);
		
		System.out.println("Getting user by userID");
		User existingUser = userRepository.findByuserName(user.getUserName());
		if(existingUser!=null) {
			System.out.println("Username Already Used By :{}" +existingUser);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User userObj = userRepository.save(user);
		System.out.println("Object saved is " + userObj);
		
		System.out.println("Saving User Details for new user : {}"+userObj);

		UserDetails userDetails = userDetailsRepository.save(new UserDetails(userObj.getUserName(),new ArrayList<Board>()));
		System.out.println("Saved user Details are : {}"+userDetails);
		return new ResponseEntity<>(userObj, HttpStatus.OK);
	}
	
	@GetMapping
	public List<User> findAllUsers(){
		System.out.println("UserController(MyCont) -> findAllUsers");

		
		System.out.println("Request for get all users");
		List<User> list = userRepository.findAll();
		System.out.println("Response for get all "+list);
		return list;
		
	}

}
