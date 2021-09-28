package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Product;
import com.springboot.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductRestController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	@ResponseBody
	public ResponseEntity<List<Product>> findAll() {
		List<Product> lista = productService.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/newProduct")	
	@ResponseBody
	public ResponseEntity<Product> insertProduct(@RequestBody Product obj) {
		
		Product objSalida = productService.insertProduct(obj);
		
		if (objSalida == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(objSalida);
		}		
		
	}
	
	@DeleteMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity<Product> delete(@PathVariable("id")int idProduct){
        Optional<Product> optProduct = productService.getById(idProduct);
        if(optProduct.isPresent()) {
            productService.deleteProduct(idProduct);
            return ResponseEntity.ok(optProduct.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }	
	
	@PutMapping("/updateProduct")	
	@ResponseBody
	public ResponseEntity<String> updateProduct(@RequestBody Product obj) {
		
		Product objSalida = productService.insertProduct(obj);
		String mensaje = "Se actualizó correctamente "+obj.getId();
		
		if (objSalida == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(mensaje);
		}		
		
	}
	
}
