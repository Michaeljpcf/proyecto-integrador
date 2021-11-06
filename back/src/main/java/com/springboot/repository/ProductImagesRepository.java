package com.springboot.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.entity.ProductImages;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer>{
	
	//@Query("select a.images from ProductImages a where a.product.id =:par_idPro LIMIT 1")
	
	@Query(value = "SELECT a.images from product_images a join products p on p.id = a.idProduct where a.idProduct= ?1 LIMIT 1;", nativeQuery = true)
	public abstract byte[] findTop1ImagesByProducto_Id(int id);
	
	
	@Query(value = "SELECT a.images from product_images a join products p on p.id = a.idProduct where a.idProduct= ?1 LIMIT 1;", nativeQuery = true)
	public abstract String findTop1ImagesByProducto_IdStr(int id);
	
	
	
	//Metodo devolver lista de imagenes
	@Query(value = "SELECT a.images from product_images a join products p on p.id = a.idProduct where a.idProduct= ?1", nativeQuery = true)
	public abstract List<String> findListofProductsById(int id); 
	
	
	/*@Query(value = "SELECT a from product_images a join products p on p.id = a.idProduct where a.idProduct= ?1;", nativeQuery = true)
	public abstract ProductImages findProductListById(int id); 
	
	public abstract List<ProductImages> findProductByProductId(int id);*/
	
}
