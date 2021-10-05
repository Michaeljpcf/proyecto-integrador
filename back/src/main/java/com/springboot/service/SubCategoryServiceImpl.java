package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.SubCategory;
import com.springboot.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}
	@Override
	public List<SubCategory> findByIdCategory(int id) {
		return subCategoryRepository.findByCategoryId(id);
	}

	
	
	

}
