package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.ProductImages;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer>{
	
}
