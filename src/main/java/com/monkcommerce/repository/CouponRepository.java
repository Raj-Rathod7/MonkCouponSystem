package com.monkcommerce.repository;

import com.monkcommerce.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
