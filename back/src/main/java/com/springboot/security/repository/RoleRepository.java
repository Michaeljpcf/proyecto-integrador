package com.springboot.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.entity.Role;
import com.springboot.security.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(RoleName roleName);

}
