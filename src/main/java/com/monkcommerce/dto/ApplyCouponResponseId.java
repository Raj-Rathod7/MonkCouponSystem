package com.monkcommerce.dto;

import java.util.List;

public class ApplyCouponResponseId {

	
	private List<CartItems> items;
    private double totalPrice;
    private double discount;
    private double finalPrice;
    
    
    
	public List<CartItems> getItems() {
		return items;
	}
	public void setItems(List<CartItems> items) {
		this.items = items;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
    
    
}
