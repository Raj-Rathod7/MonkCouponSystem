package com.monkcommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkcommerce.dto.ApplicableCouponResponse;
import com.monkcommerce.dto.ApplyCouponResponseId;
import com.monkcommerce.dto.CartRequest;
import com.monkcommerce.dto.CouponRequest;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.service.ApplicableCouponService;
import com.monkcommerce.service.ApplyCouponIdService;
import com.monkcommerce.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;
    private final ApplicableCouponService applicableCouponService;
    private final ApplyCouponIdService applyCouponIdService;

    public CouponController(CouponService couponService, ApplicableCouponService applicableCouponService, ApplyCouponIdService applyCouponIdService ) {
        this.couponService = couponService;
        this.applicableCouponService = applicableCouponService;
        this.applyCouponIdService = applyCouponIdService;
    
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

        
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon with id " + id + " deleted successfully");
    }
    
    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<ApplicableCouponResponse>> getApplicableCoupons(@RequestBody CartRequest cart) {
        return ResponseEntity.ok(applicableCouponService.getApplicableCoupons(cart));
    }
    
    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<ApplyCouponResponseId> applyCoupon(
            @PathVariable Long id,
            @RequestBody CartRequest cart) {
        return ResponseEntity.ok(applyCouponIdService.applyCoupon(id, cart));
    }
    
}