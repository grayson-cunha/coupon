package com.coupon.service;

import com.coupon.domain.Coupon;
import com.coupon.exception.CouponNotFoundException;
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

    public com.coupon.entity.Coupon delete(Long id) {
        var coupon = this.couponRepository.findById(id);

        if(coupon.isEmpty()) throw new CouponNotFoundException("Coupon com o id " + id +" não encontrado.");

        this.couponRepository.delete(coupon.get());

        return coupon.get();
    }
}
