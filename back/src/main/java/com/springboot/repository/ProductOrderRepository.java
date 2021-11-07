package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{
	
	
		//Metodo devolver lista de products Order de usuario producto
		@Query(value = "select * from product_order p join products pr on p.product_id = pr.id where pr.user_id = ?1", nativeQuery = true)
		public abstract List<ProductOrder> findOrderListByProductUserId(int id); 
		
		
		
		//Metodo devolver lista de products Order de usuario producto
		@Query(value = "select * from product_order p   where p.user_idBuyer = ?1", nativeQuery = true)
		public abstract List<ProductOrder> findOrderListByUserIdBuyer(int id); 


}
