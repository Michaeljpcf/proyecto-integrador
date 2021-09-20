package com.springboot.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
	
	private String token;
	private String bearer = "Bearer";
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.userName = userName;
		this.authorities = authorities;
	}	
	

}
