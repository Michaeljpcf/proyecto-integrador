package com.springboot.security.entity;

import java.io.Serializable;
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


//@JsonPropertyOrder
@JsonInclude(content = Include.NON_NULL)
@Entity
public class User implements Serializable{	

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
	
	//@JsonIgnore
	@NotNull
	private String password;
	
	private String picture; 
	private String country;
	private String city;
	private String phone;
	private String address;
	private String wishlist;
	private Boolean enabled;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_created_user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_updated_user;
	
	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	@JsonIgnore
	private Set<Role> roles = new HashSet<>();
	
	//@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
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

	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWishlist() {
		return wishlist;
	}

	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDate_created_user() {
		return date_created_user;
	}

	public void setDate_created_user(Date date_created_user) {
		this.date_created_user = date_created_user;
	}

	public Date getDate_updated_user() {
		return date_updated_user;
	}

	public void setDate_updated_user(Date date_updated_user) {
		this.date_updated_user = date_updated_user;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}



	private static final long serialVersionUID = 1L;
	
	
}


















