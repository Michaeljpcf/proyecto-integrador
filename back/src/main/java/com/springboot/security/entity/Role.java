package com.springboot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.springboot.security.enums.RoleName;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public Role() {
		super();
	}

	public Role(RoleName roleName) {
		super();
		this.roleName = roleName;
	}

}
