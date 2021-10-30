package com.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Product;
import com.springboot.entity.ProductImages;
import com.springboot.repository.ProductImagesRepository;
import com.springboot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImagesRepository proImagesRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).orElse(null);
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

	@Override
	@Transactional
	public Product insertProductImages(Product obj, List<MultipartFile> lstImages) {
		
		Product objProduct  = null;
		try {
			objProduct = productRepository.save(obj);
			ProductImages objImages = null;
			for(MultipartFile aux : lstImages) {
				objImages = new ProductImages();
				objImages.setProduct(objProduct);
				objImages.setImages(aux.getBytes());
				proImagesRepository.save(objImages);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return objProduct;
					
	}

	
	@Override
	public byte[] getProductImageByProductId(int id) {
		return proImagesRepository.findTop1ImagesByProducto_Id(id);
	}

	

}
