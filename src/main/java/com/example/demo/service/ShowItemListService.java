package com.example.demo.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.domain.Original;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OriginalRepository;





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
	private OriginalRepository originalRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Original> loadByOffset(int offset){
	return originalRepository.loadByOffset(offset);
		
	}
	
	
	/**
	 * 検索なしで商品を30件づつ表示する.
	 * 
	 * @param page 表示させたいページ数
	 * @param size1ページに表示させる商品数
	 * @return
	 */
	public Page<Original> showItemListPaging(int page){
	int offset=(page-1)*30;
	List<Original> list=originalRepository.loadByOffset(offset);System.out.println(list.size());
	Page<Original>  showItemList=new PageImpl<Original>(list, PageRequest.of(page,30),originalRepository.count());
	return showItemList;
	}
	
	
	/**
	 * 大カテゴリ名の検索
	 * @param name1
	 * @return
	 */
	public List<Original> daiCategoryList(String name1){
		return originalRepository.categoryNameList(name1);
	}
  
   
	/**
	 * 曖昧検索とカテゴリ検索.
	 * 
	 * @param name
	 * @param brand
	 * @return
	 */
	public Page<Original>  showItemList(String categoryName,String itemName,String brand,int page){
		int offset=(page-1)*30;
    	List<Original> list=originalRepository.showItemList(categoryName, brand, itemName, offset);
    	Page<Original>  showItemList=new PageImpl<Original>(list, PageRequest.of(page,30),originalRepository.count());
    	return showItemList;
	}
	
}
