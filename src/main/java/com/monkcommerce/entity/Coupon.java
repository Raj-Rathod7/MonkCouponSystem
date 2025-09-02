package com.monkcommerce.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String type; // cart-wise, product-wise, bxgy

    @Lob
    @Column(nullable = false)
    private String details; // store JSON as text

    public Coupon() {}

    public Coupon(Long id, String type, String details) {
        this.id = id;
        this.type = type;
        this.details = details;
    }

    public Long getId() 
    { 
    	return id; 
    }
    public void setId(Long id) 
    { 
    	this.id = id; 
    }

    public String getType() 
    { 
    	return type; 
    }
    public void setType(String type) 
    { 
    	this.type = type; 
    }

    public String getDetails() 
    { 
    	return details; 
    }
    public void setDetails(String details) 
    { 
    	this.details = details; 
    }
}