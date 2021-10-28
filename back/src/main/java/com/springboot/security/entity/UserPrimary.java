package com.springboot.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrimary implements UserDetails {
	
	
	private int idUser;
	
	private String name;
	private String userName;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;	
	
	public UserPrimary(String name, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities, int idUser) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.idUser = idUser;
	}

	//Asigna privilegios a cada usuario
	public static UserPrimary build(User user) {
		List<GrantedAuthority> authorities =
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role
						.getRoleName().name())).collect(Collectors.toList());
		
		return new UserPrimary(user.getName(), user.getUserName(), user.getEmail(), user.getPassword(), authorities, user.getIdUser());
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	

}
