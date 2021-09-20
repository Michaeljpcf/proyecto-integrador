package com.springboot.security.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;	
	
	@NotNull
	private String name; 
	
	@NotNull
	@Column(unique = true)
	private String userName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	private String picture; 
	private String country;
	private String city;
	private String phone;
	private String address;
	private String token;
	private String method;
	private String wishlist;
	private Boolean enabled;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date date_created_user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated_user;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(String name, String userName, String email, String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	
	
	
}


















