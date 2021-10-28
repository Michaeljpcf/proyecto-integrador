package com.springboot.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.User;
import com.springboot.security.entity.UserPrimary;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	// Obtiene el usuario de la DB y convierte en un Usuario Principal, que es la clase específica que utiliza Spring Security
	//para obtener los datos y los privilegios del usuario. 
	
	@Autowired
	UserService userService;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUserName(username).get();
		return UserPrimary.build(user);
	}

}
