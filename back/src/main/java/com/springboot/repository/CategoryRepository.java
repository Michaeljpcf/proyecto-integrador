package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
