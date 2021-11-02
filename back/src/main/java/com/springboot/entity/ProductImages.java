package com.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_images")
public class ProductImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idImages;
	
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	private String images;
	
	@ManyToOne
	@JoinColumn(name = "idProduct")
	private Product product;

	public int getIdImages() {
		return idImages;
	}

	public void setIdImages(int idImages) {
		this.idImages = idImages;
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	

}
