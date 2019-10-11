package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 商品詳細を示すコントローラ.
 * 
 * @author kyoul
 *
 */
@Controller
@RequestMapping("/showItemDetail")
public class ShowItemDetailController {


@Autowired
private ItemRepository itemRepository;


	/**
	 * 商品詳細画面を表示する.
	 * @return
	 */
	@RequestMapping("/detail")
	public String showItemDetail(int id,Model model) {
		Item itemDetail=itemRepository.showDetailItem(id);
		model.addAttribute("itemDetail",itemDetail);
		return "detail";
	
}
}
