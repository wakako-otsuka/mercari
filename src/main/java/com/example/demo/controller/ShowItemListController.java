package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.Original;
import com.example.demo.repository.OriginalRepository;
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
	private OriginalRepository originalRepository;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 検索による商品一覧画面.
	 * 
	 * @param model
	 * @param page
	 * @param name2
	 * @param name3
	 * @param name3
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(Model model, String categoryName, String itemName, String brand, Integer page,String name1,String name2,String name3) {

		// ページング
		if (page == null) {
			page = 1;
		}

		// 検索時のページング
		Page<Original> itemPage = showItemListService.showItemList(categoryName, itemName, brand, page);
		model.addAttribute("itemPage", itemPage);

		// 全てのページ数
		int totalPages = itemPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		// 現在のページ
		int nowPage = itemPage.getNumber();
		model.addAttribute("nowPage", nowPage);

		// 大カテゴリー名の取得.
		List<Category> daiCategoryList = categoryService.daiCategoryList();
		model.addAttribute("daiCategoryList", daiCategoryList);
		
		//中カテゴリ名の取得
		List<Category> tyuCategoryList=categoryService.tyuCategoryList(name1);
		model.addAttribute("tyuCategoryList", tyuCategoryList);
		
		//小カテゴリ名の取得
		List<Category> syouCategoryList=categoryService.syouCategoryList(name2,name3);
		model.addAttribute("syouCategoryList",syouCategoryList );
		
		return "list";

	}

}
