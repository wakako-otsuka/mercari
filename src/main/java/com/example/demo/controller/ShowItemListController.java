package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ShowItemListService;

/**
 * 商品一覧画面を操作するコントローラ.
 * 
 * @author kyoul
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;
		
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 商品一覧画面(ページング有り).
	 * 
	 * @param offset
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String index(Integer page,Model model) {
		
		if(page==null) {
			page=1;
		}
	Page<Item> itemPage=showItemListService.showItemListPaging(page);
	model.addAttribute("itemPage", itemPage);
	
	int totalPages = itemPage.getTotalPages();
	model.addAttribute("totalPages", totalPages);
	
	int nowPage=itemPage.getNumber();
	model.addAttribute("nowPage", nowPage);
	
	return "list.html";
	}
	@RequestMapping("/showList")
	public String showList(Model model,Integer page,String name1,String name2,String name3) {
		
		//ページング
				if(page==null) {
					page=1;
				}
//			Page<Item> itemPage=showItemListService.showItemListPaging(page);
		//model.addAttribute("itemPage", 1);
			
//			int totalPages = itemPage.getTotalPages();
		model.addAttribute("totalPages", 1);
//			
//			int nowPage=itemPage.getNumber();
			model.addAttribute("nowPage", 1);
			
			//大カテゴリー名の取得.
			List<Category> daiCategoryList=categoryService.daiCategoryList();
			model.addAttribute("daiCategoryList", daiCategoryList);
			
			//大カテゴリの商品の一覧
			
			// もし小カテゴリーがしていされていたら小で検索
			// そうでなければ、もし中カテゴリーが指定されていたら中で検索
			// そうでなければ、もし大カテゴリーが指定されていたら大で検索
			// そうでなければ、全件検索
			
		if(name3!=null) {
		List<Item> itemList=showItemListService.syou(name1, name2, name3);
		model.addAttribute("itemList",itemList);
	}else if(name2!=null){
		List<Item> itemList=showItemListService.tyu(name1, name2);
		model.addAttribute("itemList",itemList);	
	}else if(name1!=null) {
		List<Item> itemList=showItemListService.dai(name1);
		model.addAttribute("itemList", itemList);
	}else {
		List<Item> itemList=showItemListService.dai(name1);
		model.addAttribute("itemList", itemList);		
		}
		return "list";
		
	}
	
	}

