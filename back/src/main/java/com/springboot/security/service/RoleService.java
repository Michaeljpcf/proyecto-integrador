package com.springboot.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.Role;
import com.springboot.security.enums.RoleName;
import com.springboot.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository rolRepository;
	
	public Optional<Role> getByRoleName(RoleName roleName) {
		return rolRepository.findByRoleName(roleName);
	}
	
	public void save(Role role) {
		rolRepository.save(role);
	}
	
}
