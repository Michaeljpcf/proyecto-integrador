package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("select p from Product p where  (:p_name is '' or p.name like :p_name)"
									+ "and (:p_subCat is 0 or p.subCategory.category.id = :p_subCat)"
									+ "and ((:p_price1 is 0.0 and :p_price2 is 0.0) or p.price between :p_price1 and :p_price2)")
	public List<Product> listProductWithParams(
			@Param("p_name")String name,
			@Param("p_subCat")int cat,
			@Param("p_price1")double price1,
			@Param("p_price2")double price2);
	
}
