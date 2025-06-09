package org.cdac.acts.dao;

public class Product {
	int categoryId;
	int productId;
	float price;
	
	public Product(int categoryId, int productId, float price) {
		this.categoryId = categoryId;
		this.productId = productId;
		this.price = price;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
