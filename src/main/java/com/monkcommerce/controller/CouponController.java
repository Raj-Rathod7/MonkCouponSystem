package com.monkcommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkcommerce.dto.CouponRequest;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody CouponRequest request) {
        return ResponseEntity.ok(couponService.createCoupon(request));
    }
    
    @GetMapping
    public ResponseEntity<List<Coupon>> getCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id)
    {
    	return ResponseEntity.ok(couponService.getCouponById(id));
    }

        
    
    
}