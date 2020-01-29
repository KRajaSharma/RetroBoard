package com.company.retrospective.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.retrospective.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("JwtUserDetailsService -> loadUserByUsername");
		
		com.company.retrospective.model.User existingUser = userRepository.findByuserName(username);
		if(existingUser !=null) {
			return new User(existingUser.getUserName(), existingUser.getPassword(),  new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found with Username: "+username);

		}
		


		/*
		 * if("testUser".equals(username)) { return new User("testUser",
		 * "$2a$10$ewdsqfUbACt9sryiyh2T.OTzi9EeR15dAnocSgSS2HKwcSCNfyRca", new
		 * ArrayList<>()); }else { throw new
		 * UsernameNotFoundException("User not found with Username: "+username); }
		 */		
	}

}
