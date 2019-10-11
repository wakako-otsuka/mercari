package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 商品詳細ページを示すサービス.
 * @author kyoul
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品詳細画面を表示する.
	 * 
	 * @param name
	 * @return
	 */
	public Item showItemDetail(int id) {
		return itemRepository.showDetailItem(id);
	}
	
	

}
