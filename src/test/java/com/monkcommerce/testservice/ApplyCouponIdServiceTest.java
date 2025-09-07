package com.monkcommerce.testservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.dto.CartItems;
import com.monkcommerce.dto.CartRequest;
import com.monkcommerce.dto.ApplyCouponResponseId;
import com.monkcommerce.entity.Coupon;
import com.monkcommerce.repository.CouponRepository;
import com.monkcommerce.service.ApplyCouponIdService;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApplyCouponIdServiceTest {

    @Test
    void testCartWiseCouponApplied() throws Exception {
        
        CouponRepository repo = mock(CouponRepository.class);
        ObjectMapper mapper = new ObjectMapper();
        ApplyCouponIdService service = new ApplyCouponIdService(repo, mapper);

        
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setType("cart-wise");
        coupon.setDetails("{\"threshold\":100,\"discount\":10}");

        
        when(repo.findById(1L)).thenReturn(Optional.of(coupon));

        
        CartRequest cart = new CartRequest(Arrays.asList(
                new CartItems(1L, 2, 50),   
                new CartItems(2L, 1, 100)   
        ));

        
        ApplyCouponResponseId result = service.applyCoupon(1L, cart);

        
        assertEquals(200.0, result.getTotalPrice(), 0.01);
        assertEquals(20.0, result.getDiscount(), 0.01);
        assertEquals(180.0, result.getFinalPrice(), 0.01);
    }
}