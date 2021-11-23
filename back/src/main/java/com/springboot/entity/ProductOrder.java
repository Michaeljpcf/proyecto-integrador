package com.springboot.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

@Entity
@Getter
@Setter
@Table(name = "product_order")
public class ProductOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nameUser;
	private String lastNameUser;
	private String emailUser;
	private String address1;
	private String address2;
	private String phoneContact;
	
	private int statePO;
	
	@JsonFormat(pattern = "dd-MM-yyyy",timezone="America/Lima")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy",timezone="America/Lima")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDateNow;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product_id;
	
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private String productImage;

	private double total;
	
	private String creditName;
	private String creditCarNumber;
	@JsonFormat(pattern = "dd-MM-yyyy",timezone="America/Lima")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationCardDate;
	private String CVV;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_idBuyer")
	private User userBuyer;
	
	
	
	
}
