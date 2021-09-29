package com.springboot.security.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.springboot.entity.Product;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(content = Include.NON_NULL)
//@JsonPropertyOrder
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
	@JsonIgnore
	private String password;
	
	private String picture; 
	private String country;
	private String city;
	private String phone;
	private String address;
	private String wishlist;
	private Boolean enabled;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_created_user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_updated_user;
	
	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	@JsonIgnore
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	//@JsonManagedReference
	private List<Product> products;

	public User() {
		this.products = new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.date_created_user = new Date();
	}

	public User(String name, String userName, String email, String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	
	
	
}


















