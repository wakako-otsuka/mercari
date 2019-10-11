package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

/**
 * itemテーブルを操作するレポジトリ.
 * 
 * @author otsuka
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
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
	return item;
};

/**
 * 商品詳細ページの表示.
 * @return
 */
public Item showDetailItem(int id){
	 StringBuilder sql=new StringBuilder(); 
	 sql.append("SELECT id,name, condition,category,brand,price,shipping,description FROM items WHERE id=:id;");
     SqlParameterSource param =new MapSqlParameterSource().addValue("id", id);
     Item detailItem=template.queryForObject(sql.toString(), param, ITEM_ROW_MAPPER);
     return detailItem;
}


	
	
}
