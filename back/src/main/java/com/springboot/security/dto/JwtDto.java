package com.springboot.security.dto;

import java.util.Date;

import com.springboot.security.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto extends User {
	
	private String token;
	private int idUser;
	private String name; 
	private String userName;
	private String email;
	private Date date_created_user;
	//private String bearer = "Bearer";
	//private String userName;
	//private Collection<? extends GrantedAuthority> authorities;
	
	
//	public JwtDto(String token, int idUser, String name, String userName, String email, Date date_created_user) {
//		super();
//		this.token = token;
//		this.idUser = idUser;
//		this.name = name;
//		this.userName = userName;
//		this.email = email;
//		this.date_created_user = date_created_user;
//	}
	
	
	
	public JwtDto(String token) {
		super();
		this.token = token;
	}	
	

}
