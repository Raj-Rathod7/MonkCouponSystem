package com.monkcommerce.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.dto.ApplyCouponResponseId;
import com.monkcommerce.dto.CartItems;
import com.monkcommerce.dto.CartRequest;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.repository.CouponRepository;

@Service
public class ApplyCouponIdService {

    private final CouponRepository couponRepository;
    private final ObjectMapper objectMapper;

    public ApplyCouponIdService(CouponRepository couponRepository, ObjectMapper objectMapper) {
        this.couponRepository = couponRepository;
        this.objectMapper = objectMapper;
    }

    public ApplyCouponResponseId applyCoupon(Long id, CartRequest cart) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        double total = 0;
        for (CartItems item : cart.getItems()) {
            total += item.getQuantity() * item.getPrice();
        }

        double discount = 0;

        try {
            JsonNode details = objectMapper.readTree(coupon.getDetails());
            if ("cart-wise".equals(coupon.getType())) {
                double threshold = details.get("threshold").asDouble();
                double percent = details.get("discount").asDouble();
                if (total >= threshold) {
                    discount = (total * percent) / 100.0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplyCouponResponseId response = new ApplyCouponResponseId();
        response.setItems(cart.getItems());
        response.setTotalPrice(total);
        response.setDiscount(discount);
        response.setFinalPrice(total - discount);

        return response;
    }
}