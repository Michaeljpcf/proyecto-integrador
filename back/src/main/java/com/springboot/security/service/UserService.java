package com.springboot.security.service;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.User;
import com.springboot.security.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Optional<User> getByUserName(String userName) {
//		var a = userRepository.findByUserName(userName);
//		a.get().getRoles();
//		return a;
		return userRepository.findByUserName(userName);
	}
	
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}

}
