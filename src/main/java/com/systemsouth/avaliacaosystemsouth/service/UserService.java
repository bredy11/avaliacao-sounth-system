package com.systemsouth.avaliacaosystemsouth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User creatUser (String document) {
		User user = new User();
		user.setDocument(document);
		
		return userRepository.save(user);
	}

	
	public User findbyDocument(String document) {
		return userRepository.findByDocument(document);
	}
	
	
	
}
