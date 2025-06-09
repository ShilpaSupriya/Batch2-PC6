package org.cdac.acts.dao;

import java.util.ArrayList;

public class CartImpl implements Cart{
	
	ArrayList<Product> productObj;
	
	public CartImpl(ArrayList<Product> productObj) {
		this.productObj = productObj;
	}

	@Override
	public void addProduct(Product cartObj) {
		
	}

	@Override
	public void removeProduct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quantity(Product cartObj) {
		// TODO Auto-generated method stub
		
	}

}
