package com.coupon.service;

import com.coupon.domain.Coupon;
import com.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public com.coupon.entity.Coupon create(Coupon coupon) {
        coupon.validateCode();

        return this.couponRepository.save(coupon.toEntity());
    }
}
