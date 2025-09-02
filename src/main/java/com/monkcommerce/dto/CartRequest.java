package com.monkcommerce.dto;

import java.util.List;

public class CartRequest {

	private List<CartItems> items;

    public CartRequest() {}

    public CartRequest(List<CartItems> items) {
        this.items = items;
    }

	public List<CartItems> getItems() {
		return items;
	}

	public void setItems(List<CartItems> items) {
		this.items = items;
	}
	
    
    
	
}
