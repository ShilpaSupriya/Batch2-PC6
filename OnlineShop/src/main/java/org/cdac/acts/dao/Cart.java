package org.cdac.acts.dao;

public interface Cart {
	public void addProduct(Product cartObj);
	public void removeProduct();
	public void quantity(Product cartObj);
}
