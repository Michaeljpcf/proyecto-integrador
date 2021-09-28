package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(int idProduct);
	
	public Product insertProduct(Product obj);	
	
	public void deleteProduct(int idProduct);
	
	public Optional<Product> getById(int idProduct);

}
