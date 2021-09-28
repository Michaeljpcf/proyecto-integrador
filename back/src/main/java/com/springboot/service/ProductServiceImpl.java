package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	public Product findById(int idProduct) {
		return null; 
	}

	@Override
	public Product insertProduct(Product obj) {
		return productRepository.save(obj);
	}

	@Override
	public void deleteProduct(int idProduct) {
		productRepository.deleteById(idProduct);		
	}

	@Override
	public Optional<Product> getById(int idProduct) {
		return productRepository.findById(idProduct);
	}

	

}
