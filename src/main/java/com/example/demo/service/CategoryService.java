package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;

/**
 * カテゴリーを操作するサービス.
 * 
 * @author kyoul
 *
 */
@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * 大カテゴリーの一覧.
	 * @return
	 */
	public List<Category> daiCategoryList(){
		return categoryRepository.loadByDai();
	}
	
	/**
	 * 中カテゴリーの一覧.
	 * @return
	 */
	public List<Category> tyuCategoryList(String name){
		return categoryRepository.loadByTyu(name);
	}
	
	/**
	 * 小カテゴリーの一覧.
	 * @return
	 */
	
	 public List<Category> syouCategoryList(String name1,String name2){ 
		 return categoryRepository.loadBySyou(name1,name2); 
	 }
	 
	
	
	}