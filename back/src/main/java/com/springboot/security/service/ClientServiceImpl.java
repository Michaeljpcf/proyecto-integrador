package com.springboot.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.User;
import com.springboot.security.repository.UserRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByIdUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	

}
