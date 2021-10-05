package com.springboot.service;

import java.util.List;
import com.springboot.entity.SubCategory;

public interface SubCategoryService {
	
	public List<SubCategory> findAll();
	
	public List<SubCategory> findByIdCategory(int id);

}
