package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Original;

/**
 * Originalテーブルを操作するレポジトリー.
 * 
 * @author otsuka
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Originalオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Original> Original_ROW_MAPPER = (rs, i) -> {
		Original original = new Original();
		original.setId(rs.getInt("id"));
		original.setName(rs.getString("name"));
		original.setConditionId(rs.getInt("condition_id"));
		original.setCategoryName(rs.getString("category_name"));
		original.setBrand(rs.getString("brand"));
		original.setPrice(rs.getInt("price"));
		original.setShipping(rs.getInt("shipping"));
		original.setDescription(rs.getString("description"));

		return original;
	};

	/**
	 * 検索なしで商品を30件づつ表示する.
	 * 
	 * @param name
	 * @return
	 */
	public List<Original> loadByOffset(int offset) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id, name,condition_id,category_name,brand,price,shipping,description from original  ORDER BY id limit 30 offset :offset;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);
		List<Original> itemList = template.query(sql.toString(), param, Original_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 全商品の数.
	 * 
	 * @return
	 */
	public Integer count() {
		StringBuilder sql = new StringBuilder();
		SqlParameterSource param = null;
		sql.append("SELECT count(*) FROM original ;");
		Integer count = template.queryForObject(sql.toString(), param, Integer.class);
		return count;
	}

	/**
	 * 大カテゴリーの取得.
	 * 
	 * @return
	 */

	public List<Original> categoryNameList(String name1) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c.name from category c inner join original o on c.name_all=o.category_name ");
		sql.append(
				"where split_part(c.name_all,'/', 1) IN (select split_part(o.category_name,'/', 1) from original o WHERE o.name=:name1);");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name1", name1);
		List<Original> list = template.query(sql.toString(), param, Original_ROW_MAPPER);
		return list;
	}
	

	/**
	 * 曖昧検索とカテゴリ検索.
	 * 
	 * @param name
	 * @param brand
	 * @param offset
	 * @return
	 */
	public List<Original> showItemList(String categoryName, String brand, String itemName, Integer offset) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,condition_id,category_name,brand,price,shipping,description from original");
		sql.append(" where 1 = 1 ");
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", "%categoryName%")
				.addValue("itemName", "%itemName%")
				.addValue("brand", brand)
				.addValue("offset", offset);

		// カテゴリ検索
		if (categoryName != null) {
			sql.append(" and category_name like :categoryName ");
		}
		// 商品名の曖昧検索
		if (itemName != null) {
			sql.append(" and name LIKE :itemName ");
		}
		// ブランド名検索
		if (brand != null) {
			sql.append("and brand=:brand");
		}
		sql.append(" limit 30 offset :offset");

		List<Original> list = template.query(sql.toString(), param, Original_ROW_MAPPER);
		return list;

	}
}