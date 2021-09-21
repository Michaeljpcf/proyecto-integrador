package com.springboot.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
	
	private String token;
	//private String bearer = "Bearer";
	//private String userName;
	//private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDto(String token) {
		super();
		this.token = token;
	}	
	

}
