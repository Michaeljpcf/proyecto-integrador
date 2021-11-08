package com.springboot.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.security.entity.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String url;	
	private Double price;
	private String description;
	private String image;
	private String condition;
	
	private int stock;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	List<String> lstProductImages;
	//private List<ProductImages> productImages;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	@JsonFormat(pattern = "dd-MM-yyyy",timezone="America/Lima")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryRangeDate;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategories_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private SubCategory subCategory;	
	
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name ="category_id")
//	private Category category;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
		
}
















