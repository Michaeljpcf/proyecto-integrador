package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.SubCategory;
import com.springboot.service.SubCategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SubCategoryRestController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/subcategories")
	@ResponseBody
	public ResponseEntity<List<SubCategory>> findAll() {
		List<SubCategory> lista = subCategoryService.findAll();
		return ResponseEntity.ok(lista);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
