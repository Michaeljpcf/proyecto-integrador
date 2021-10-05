package com.springboot.security.service;

import java.util.List;
import java.util.Optional;

import com.springboot.security.entity.User;

public interface ClientService {
	
	public List<User> findAll();
	
	public abstract Optional<User> findById(int id);
	
	public User findByIdUser(Integer id);
	
	public User save(User user);
	
	public void delete(int id);

}
