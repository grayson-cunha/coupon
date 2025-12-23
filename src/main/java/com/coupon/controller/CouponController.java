package com.coupon.controller;

import com.coupon.domain.Coupon;
import com.coupon.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping()
    public ResponseEntity<com.coupon.entity.Coupon> create(@Valid @RequestBody Coupon coupon) {
        var newCoupon = this.couponService.create(coupon);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<com.coupon.entity.Coupon> create(@PathVariable("id") Long id) {
        var removedCoupon = this.couponService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(removedCoupon);
    }
}
