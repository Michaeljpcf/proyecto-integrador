package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Product;
import com.springboot.entity.ProductImages;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(Integer id);
	
	public Product insertProduct(Product obj);	
	
	public void deleteProduct(int idProduct);
	
	public Optional<Product> getById(int idProduct);
	
	public Product insertProductImages(Product obj,List<MultipartFile> lstImages);
	
	public byte[] getProductImageByProductId(int id);

}
