package com.springboot.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Product findById(Integer id);
	
	public Product insertProduct(Product obj);	
	
	public void deleteProduct(int idProduct);
	
	public Optional<Product> getById(int idProduct);
	
	public void insertProductImages(Product obj,List<MultipartFile> lstImages) throws IOException;
	
	public byte[] getProductImageByProductId(int id);
	
	//Obtener foto lista
	public List<String> getObjectFromS3();
	
	//Descargar el file
	public InputStream downloadFile(String key);
	
	//Obtener una foto
	public String getImageByProductId(int productId);
	
	//Obtener todos los productos con 1 foto
	public List<Product> getProductsWith1Image();
	
	//Obtener lista de los productos con imagen usando 3 parametros
	public List<Product> getProductosWithParams(String name,int subCat,int cat,double price1,double price2);

}
