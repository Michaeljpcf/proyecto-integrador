package com.springboot.service;

import java.util.List;

import com.springboot.entity.ProductOrder;

public interface ProductOrderService {

	
	public List<ProductOrder> productOrderListByProductId(int id);
	
	public ProductOrder insertProductOrder(ProductOrder obj);
	
	public List<ProductOrder> findAll();
	
	public ProductOrder findById(int id);
	
	
	public List<ProductOrder> findOrderByIdBuyer(int id);
	
	
}
