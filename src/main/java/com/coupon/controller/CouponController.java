package com.coupon.controller;

import com.coupon.domain.Coupon;
import com.coupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping()
    public ResponseEntity<com.coupon.entity.Coupon> create(@RequestBody Coupon coupon) {
        var newCoupon = this.couponService.create(coupon);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
    }
}
