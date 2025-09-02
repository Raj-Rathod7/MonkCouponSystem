package com.monkcommerce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.repository.CouponRepository;
import com.monkcommerce.dto.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CouponService(CouponRepository couponRepository, ObjectMapper objectMapper) {
        this.couponRepository = couponRepository;
        this.objectMapper = objectMapper;
    }

    public Coupon createCoupon(CouponRequest request) {
        try {
            String detailsJson = objectMapper.writeValueAsString(request.getDetails());
            Coupon coupon = new Coupon();
            coupon.setType(request.getType());
            coupon.setDetails(detailsJson);
            return couponRepository.save(coupon);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid details format: " + e.getOriginalMessage(), e);
        }
    }
    
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
    
    
    
}