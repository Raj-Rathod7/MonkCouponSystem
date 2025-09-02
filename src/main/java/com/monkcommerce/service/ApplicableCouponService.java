package com.monkcommerce.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.dto.*;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicableCouponService {

    private final CouponRepository couponRepository;
    private final ObjectMapper objectMapper;

    public ApplicableCouponService(CouponRepository couponRepository, ObjectMapper objectMapper) {
        this.couponRepository = couponRepository;
        this.objectMapper = objectMapper;
    }

    public List<ApplicableCouponResponse> getApplicableCoupons(CartRequest cart) {
        List<ApplicableCouponResponse> result = new ArrayList<>();

        double cartTotal = cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();

        List<Coupon> coupons = couponRepository.findAll();

        for (Coupon coupon : coupons) {
            try {
                JsonNode details = objectMapper.readTree(coupon.getDetails());
                double discount = 0;

                switch (coupon.getType()) {
                    case "cart-wise":
                        double threshold = details.get("threshold").asDouble();
                        double percent = details.get("discount").asDouble();
                        if (cartTotal >= threshold) {
                            discount = (cartTotal * percent) / 100.0;
                        }
                        break;

                    case "product-wise":
                        Long productId = details.get("product_id").asLong();
                        double productDiscount = details.get("discount").asDouble();
                        for (CartItems item : cart.getItems()) {
                            if (item.getProductId().equals(productId)) {
                                discount = (item.getQuantity() * item.getPrice() * productDiscount) / 100.0;
                                break;
                            }
                        }
                        break;

                    case "bxgy":
                        // simplified version
                        JsonNode buyProducts = details.get("buy_products");
                        JsonNode getProducts = details.get("get_products");
                        int repLimit = details.has("repition_limit") ? details.get("repition_limit").asInt() : 1;

                        if (buyProducts != null && getProducts != null) {
                            JsonNode buy = buyProducts.get(0);
                            JsonNode free = getProducts.get(0);

                            Long buyId = buy.get("product_id").asLong();
                            int buyQty = buy.get("quantity").asInt();

                            Long freeId = free.get("product_id").asLong();
                            int freeQty = free.get("quantity").asInt();

                            int buyCount = cart.getItems().stream()
                                    .filter(i -> i.getProductId().equals(buyId))
                                    .mapToInt(CartItems::getQuantity).sum();

                            int timesApplicable = Math.min(buyCount / buyQty, repLimit);

                            if (timesApplicable > 0) {
                                for (CartItems item : cart.getItems()) {
                                    if (item.getProductId().equals(freeId)) {
                                        discount = timesApplicable * freeQty * item.getPrice();
                                    }
                                }
                            }
                        }
                        break;
                }

                if (discount > 0) {
                    result.add(new ApplicableCouponResponse(
                            coupon.getId(),
                            coupon.getType(),
                            discount
                    ));
                }

            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }

        return result;
    }
}
