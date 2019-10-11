package com.example.demo.domain;

/**
 * オリジナルテーブルを示すドメイン.
 * @author kyoul
 *
 */
public class Original {
	
	private Integer id;
	
	private String name;
	
	private Integer conditionId;
	
	private String categoryName;
	
	private String brand;
	
	private Integer price;
	
	private Integer shipping;
	
	private String description;

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

	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	
	public String getDaiCategory() {
		String[] items = categoryName.split("/");
		return items[0];
	}

    public String getTyuCategory() {
    	String[] items=categoryName.split("/");
    	return items[1];
    }
    
    public String getSyouCategory() {
    	String[] items=categoryName.split("/");
    	return items[2];
    }
	
	@Override
	public String toString() {
		return "Original [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", categoryName="
				+ categoryName + ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description="
				+ description + ", getId()=" + getId() + ", getName()=" + getName() + ", getConditionId()="
				+ getConditionId() + ", getCategoryName()=" + getCategoryName() + ", getBrand()=" + getBrand()
				+ ", getPrice()=" + getPrice() + ", getShipping()=" + getShipping() + ", getDescription()="
				+ getDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}
