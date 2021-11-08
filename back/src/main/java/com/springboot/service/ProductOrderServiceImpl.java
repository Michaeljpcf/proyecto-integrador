package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.entity.Product;
import com.springboot.entity.ProductOrder;
import com.springboot.repository.ProductImagesRepository;
import com.springboot.repository.ProductOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductOrderServiceImpl implements ProductOrderService{
	
	
	@Autowired
	private ProductOrderRepository productOrderRepository;
	
	@Autowired
	private ProductImagesRepository proImagesRepository;
	

	@Override
	public List<ProductOrder> productOrderListByProductId(int id) {
		
		List<ProductOrder> lstProductOrder =productOrderRepository.findOrderListByProductUserId(id); 
		for(ProductOrder aux : lstProductOrder) {
			String image = proImagesRepository.findTop1ImagesByProducto_IdStr(aux.getProduct_id().getId());
			aux.setProductImage(image);
		}
		return lstProductOrder;
	}

	@Override
	public ProductOrder insertProductOrder(ProductOrder obj) {
		
		return productOrderRepository.save(obj);
	}

	@Override
	public List<ProductOrder> findAll() {
		return productOrderRepository.findAll();
	}

	@Override
	public List<ProductOrder> findOrderByIdBuyer(int id) {
		
		List<ProductOrder> lstProductOrder =productOrderRepository.findOrderListByUserIdBuyer(id);
		for(ProductOrder aux : lstProductOrder) {
			String image = proImagesRepository.findTop1ImagesByProducto_IdStr(aux.getProduct_id().getId());
			aux.setProductImage(image);
		}
		return lstProductOrder;
	}
	
	
	

	

}
