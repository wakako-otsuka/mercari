package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ShowItemListService;

/**
 * カテゴリーを操作するコントローラ.
 * 
 * @author otsuka
 *
 */
@Controller("")
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ShowItemListService showItemListService;
	
	 
	 /**
	  * 中カテゴリのプルダウンメソット.
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/dai/{value}",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	 @ResponseBody
	 public String changePulldown(@PathVariable("value")String value) {
		 String val=value;
		 StringBuilder s=new StringBuilder();
		 String str="";
		 s.append("[");
		 
			List<Category> tyuCategoryList=categoryService.tyuCategoryList(value);
		 for(Category category:tyuCategoryList) {
			 s.append("{\"");
			 s.append("itemValue");
			 s.append("\"");
			 s.append(":");
			 s.append("\"" + category.getName() + "\"");
			 s.append(",");
			 s.append("\"");
			 s.append("itemLabel");
			 s.append("\"");
			 s.append(":");
			 s.append("\"" + category.getName() + "\"");
			 s.append("}");
			 s.append(",");
		 }
		 s.deleteCharAt(s.lastIndexOf(","));
		    s.append("]");
		    str = s.toString();

		    return str;
		    }
		 
	/**
	 * 小カテゴリのプルダウンメソット.
	 * @param value1
	 * @param value2
	 * @return
	 */
	@RequestMapping(value="/tyu/{value1}/{value2}", method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String changePulldown(@PathVariable("value1")String value1,@PathVariable("value2")String value2) {
		String name1=value1;
		String name2=value2;
		StringBuilder m=new StringBuilder();
		String str="";
		m.append("[");
		
		List<Category> syouCategoryList=categoryService.syouCategoryList(name1,name2);
		 for(Category category:syouCategoryList) {
			 m.append("{\"");
			 m.append("itemValue");
			 m.append("\"");
			 m.append(":");
			 m.append("\"" + category.getName() + "\"");
			 m.append(",");
			 m.append("\"");
			 m.append("itemLabel");
			 m.append("\"");
			 m.append(":");
			 m.append("\"" + category.getName() + "\"");
			 m.append("}");
			 m.append(",");
		 }
		 m.deleteCharAt(m.lastIndexOf(","));
		    m.append("]");
		    str = m.toString();

		    return str;
		    }
	

}
	 
		 
	 
	 

	

