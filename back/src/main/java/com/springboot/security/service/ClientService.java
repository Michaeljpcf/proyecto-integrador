package com.springboot.security.service;

import java.util.List;

import com.springboot.security.entity.User;

public interface ClientService {
	
	public List<User> findAll();

}
