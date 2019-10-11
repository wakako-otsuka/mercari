package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

/**
 * カテゴリーテーブルを操作するレポジトリ.
 * 
 * @author otsuka
 */
@Repository
public class CategoryRepository {
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	/**
	 * カテゴリーオブジェクトを作るローマッパー.
	 */
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER=(rs,i)->{
		Category category=new Category();
		category.setNameAll(rs.getString("name_all"));
		category.setId(rs.getInt("id"));
		category.setParent(rs.getInt("parent"));
		category.setName(rs.getString("name"));
		return category;
	};
	
	/**
	 * 大カテゴリー名の一覧.
	 * @return
	 */
	public List<Category> loadByDai(){
		 StringBuilder sql=new StringBuilder();
		 sql.append("SELECT name_all,id,parent,name FROM category WHERE parent is null");
		 List<Category> daiList=template.query(sql.toString(), CATEGORY_ROW_MAPPER);
		 return daiList;
	}
	
	/**
	 * 中カテゴリ名の一覧.
	 * @return
	 */
	public List<Category> loadByTyu(String daiName){
		StringBuilder sql=new StringBuilder();
		 sql.append("SELECT name_all,id,parent,name FROM category WHERE parent IN (SELECT id FROM category WHERE name = :name)");
		 SqlParameterSource params = new MapSqlParameterSource().addValue("name",daiName);
		 List<Category> tyuList=template.query(sql.toString(), params, CATEGORY_ROW_MAPPER);
		 return tyuList;
	}
	
	
	/**
	 * 小カテゴリー名の一覧.
	 * @return
	 */
	
	 public List<Category> loadBySyou(String daiName,String tyuName) {
	 StringBuilder sql=new StringBuilder();
	 sql.append("SELECT name_all,id,parent,name FROM category WHERE parent IN (SELECT id FROM category WHERE parent IN (SELECT id FROM category WHERE name=:daiName and parent is null) and name=:tyuName);");
	 SqlParameterSource params = new MapSqlParameterSource().addValue("tyuName",tyuName).addValue("daiName",daiName);
	 List<Category> shouList=template.query(sql.toString(), params,CATEGORY_ROW_MAPPER);
	 return shouList; 
	 }
	 
	 
	
	
	 
}

