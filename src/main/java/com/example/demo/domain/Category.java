package com.example.demo.domain;

/**
 * カテゴリーを示すドメイン.
 * 
 * @author otsuka
 */
public class Category {
	
	/**大中小合体のカテゴリー名*/
	private String nameAll;
	
	/**カテゴリーID*/
	private Integer id;
	
	/**カテゴリーの親子関係*/
	private Integer parent;

	/**カテゴリー名*/
	private String name;

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [nameAll=" + nameAll + ", id=" + id + ", parent=" + parent + ", name=" + name
				+ ", getNameAll()=" + getNameAll() + ", getId()=" + getId() + ", getParent()=" + getParent()
				+ ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
