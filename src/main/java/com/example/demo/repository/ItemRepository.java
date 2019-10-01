package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;

/**
 * itemsテーブルを操作するレポジトリー.
 * 
 * @author otsuka
 *
 */
@Repository
public class ItemRepository { 
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 *Itemオブジェクトを生成するローマッパー. 
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER=(rs,i)->{
		Item item=new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setCondition(rs.getInt("condition"));
		item.setCategory(rs.getInt("category"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getInt("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
        item.setNameAll(rs.getString("name_all"));
		return item;
	};
	
	/**
	 * 商品を30件づつ表示する.
	 * 
	 * @param name
	 * @return
	 */
	public List<Item> loadByOffset(int offset) {
		 StringBuilder sql=new StringBuilder(); 
		 sql.append("SELECT i.id, i.name,i.condition,i.category,i.brand,i.price,i.shipping,i.description,c.name_all from items i  LEFT OUTER JOIN category c on c.id=i.category ORDER BY i.id limit 30 offset :offset;");	 
		SqlParameterSource param=new MapSqlParameterSource().addValue("offset",offset);
		 List<Item> itemList=template.query(sql.toString(), param,ITEM_ROW_MAPPER);
		 return itemList;
	}
	
	/**
	 * 全商品表示する.
	 * 
	 * @param name
	 * @return
	 */
	/*
	 * public List<Item> findAll() { StringBuilder sql=new StringBuilder(); sql.
	 * append("SELECT i.id, i.name,i.condition,i.category,i.brand,i.price,i.shipping,i.description,c.name_all from items i  LEFT OUTER JOIN category c on c.id=i.category ORDER BY i.id;"
	 * ); List<Item> itemList=template.query(sql.toString(),ITEM_ROW_MAPPER); return
	 * itemList; }
	 */
	
	/**
	 * 全商品の数.
	 * @return
	 */
	public Integer count(){
		 StringBuilder sql=new StringBuilder(); 
		 SqlParameterSource param=null;
		 sql.append("SELECT count(*) FROM items ;");	
		 Integer count=template.queryForObject(sql.toString(),param,Integer.class);
		 return count;
	}
	
	 /**
	  * 大カテゴリの検索による一覧
	 * @param name
	 * @return
	 */
	public List<Item> loadByDaiCategory(String name1){
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT i.id,i.name,i.condition,i.category,i.brand,i.price,i.shipping,i.description ,c.name_all ");
			sql.append("FROM items i left outer JOIN category c ON i.category=c.id ");
			sql.append("WHERE  split_part(c.name_all, '/', 1) = :name1 limit 30 offset 0");
			SqlParameterSource param=new MapSqlParameterSource().addValue("name1",name1);
			 List<Item> daiList=template.query(sql.toString(), param,ITEM_ROW_MAPPER);
			 return daiList;
		}
	
	/**
	 * 大カテゴリからの中カテゴリの検索による一覧.
	 * @param name2
	 * @return
	 */
	public List<Item> loadByTyuCategory(String name1,String name2){
	StringBuilder sql=new StringBuilder();
	sql.append("SELECT i.id,i.name,i.condition,i.category,i.brand,i.price,i.shipping,i.description ,c.name_all,c.parent ");
	sql.append("FROM items i left outer JOIN category c ON i.category=c.id "); 
	sql.append("WHERE exists (select i.name from items where split_part(c.name_all, '/', 1) = :name1) and  split_part(c.name_all, '/', 2) = :name2;");
	SqlParameterSource param=new MapSqlParameterSource().addValue("name1",name1).addValue("name2",name2);
	List<Item> tyuList=template.query(sql.toString(), param,ITEM_ROW_MAPPER);
	 return tyuList;
	}
	
	/**
	 * 大カテゴリからの中カテゴリからの小カテゴリの検索による一覧.
	 * @return
	 */
	public List<Item> loadBySyouCategory(String name1,String name2,String name3){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT i.id,i.name,i.condition,i.category,i.brand,i.price,i.shipping,i.description ,c.name_all,c.parent ");
		sql.append("FROM items i left outer JOIN category c ON i.category=c.id ");
		sql.append("WHERE exists (select i.name from items where split_part(c.name_all, '/', 1) = :name1 and  split_part(c.name_all, '/', 2) = :name2 ");
		sql.append("and split_part(c.name_all, '/', 3) =:name3)");
		SqlParameterSource param=new MapSqlParameterSource().addValue("name1",name1).addValue("name2",name2).addValue("name3",name3);
		List<Item> syouList=template.query(sql.toString(), param,ITEM_ROW_MAPPER);
		 return syouList;
	}
	
	
	
	}