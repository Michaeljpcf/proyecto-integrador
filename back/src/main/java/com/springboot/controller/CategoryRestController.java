package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Category;
import com.springboot.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	@ResponseBody
	public ResponseEntity<List<Category>> findAll() {
		List<Category> lista = categoryService.findAll();
		return ResponseEntity.ok(lista);
	}

}
