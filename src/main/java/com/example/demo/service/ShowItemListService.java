package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;



/**
 * 商品一覧画面を操作するサービス.
 * 
 * @author otsuka
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * ページング用メソット.
	 * 
	 * @param page 表示させたいページ数
	 * @param size1ページに表示させる商品数
	 * @return
	 */
	public Page<Item> showItemListPaging(int page){
	int offset=(page-1)*30;
	List<Item> list=itemRepository.loadByOffset(offset);System.out.println(list.size());
	Page<Item>  showItemList=new PageImpl<Item>(list, PageRequest.of(page,30),itemRepository.count());
	return showItemList;
	}
	 /**
	  * 大カテゴリの検索による一覧.
	 * @param name1
	 * @return
	 */
	public List<Item> dai(String name1){
		 return itemRepository.loadByDaiCategory(name1);
	 }
	
	/**
	 * 大カテゴリからの中カテゴリの検索による一覧.
	 * @param name1
	 * @param name2
	 * @return
	 */
	public List<Item> tyu(String name1,String name2){
		return itemRepository.loadByTyuCategory(name1, name2);
	}
	
	/**
	 * 大カテゴリからの中カテゴリ	からの小カテゴリの検索による一覧.
	 * @param name1
	 * @param name2
	 * @param name3
	 * @return
	 */
	public List<Item> syou(String name1,String name2,String name3){
		return itemRepository.loadBySyouCategory(name1, name2, name3);
		}

}
