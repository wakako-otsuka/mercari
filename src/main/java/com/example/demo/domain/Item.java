package com.example.demo.domain;

/**
 * 商品情報を示すドメイン.
 * 
 * @author otsuka
 *
 */
public class Item {
	
	/**商品ID*/
	private Integer id;
	
	/**名前*/
	private String name;
	
	/**状態*/
	private Integer condition;
	
	/**カテゴリー*/
	private Integer category;
	
	/**ブランド*/
	private String brand;
	
	/**値段*/
	private Integer price;
	
	/**発送方法*/
	private Integer shipping;
	
	/**説明*/
	private String description;
	
    /**全てのカテゴリー名*/
    private String nameAll;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category + ", brand="
				+ brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description + ", nameAll="
				+ nameAll + "]";
	}
    
    

}
