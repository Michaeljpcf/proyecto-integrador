package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	

}
