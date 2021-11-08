package com.springboot.controller;

import java.util.List;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.ProductOrder;
import com.springboot.security.entity.User;
import com.springboot.security.entity.UserPrimary;
import com.springboot.service.ProductOrderService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductOrderController {
	
	@Autowired
	private ProductOrderService productOrderService;
	
	
	@GetMapping("/listProductsOrder")
	@ResponseBody
	public ResponseEntity<List<ProductOrder>> findAll() {
		List<ProductOrder> lista = productOrderService.findAll();
		return ResponseEntity.ok(lista);
	}
	
	
	
	@PostMapping("/newOrderProduct")
	@ResponseBody
	public ResponseEntity<ProductOrder> insertProductOrder(@RequestBody ProductOrder obj, Authentication authentication){
		var usuarioPrincipal = (UserPrimary) authentication.getPrincipal();
		User user = new User();
		user.setIdUser(usuarioPrincipal.getIdUser());
		obj.setUserBuyer(user);
		
		obj.setDeliveryDateNow(new Date());
		
		
		ProductOrder objSalida = productOrderService.insertProductOrder(obj);
		if (objSalida == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(objSalida);
		}		
	}
	
	//Metodo que devuelve la lista de OrderList por ID de publicador
	
	@GetMapping("/findByProductIdUser")
	@ResponseBody
	public List<ProductOrder> findProductOrderByProductUserId(Authentication authentication){
		var usuarioPrincipal = (UserPrimary) authentication.getPrincipal();
		User user = new User();
		user.setIdUser(usuarioPrincipal.getIdUser());
		int idUser = user.getIdUser();
		return productOrderService.productOrderListByProductId(idUser);
	}
	
	
	//Metodo que devuelve la lista de OrderList por ID de comprador
	
	@GetMapping("/findByProductByIdBuyer")
	@ResponseBody
	public List<ProductOrder> findProductOrderByIdBuyer(Authentication authentication){
		var usuarioPrincipal = (UserPrimary) authentication.getPrincipal();
		User user = new User();
		user.setIdUser(usuarioPrincipal.getIdUser());
		int idUserBuyer = user.getIdUser();
		return productOrderService.findOrderByIdBuyer(idUserBuyer);
	}
	
}
