package com.coupon.service;

import com.coupon.domain.Coupon;
import com.coupon.exception.CouponInvalidException;
import com.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTests {

    @Mock
    private CouponRepository couponRepository;
    @InjectMocks
    private CouponService couponService;

    @Test
    public void givenCoupon_whenIsValid_thenCreate() {
        var expirationDate = LocalDateTime.now().plusDays(1);
        var coupon = new Coupon("AH43HJ", "Coupon de Natal", 0.5, expirationDate);
        var createdCoupon = new com.coupon.entity.Coupon(1L,"AH43HJ", "Cupon de Natal", 0.5, expirationDate);

        when(couponRepository.save(any())).thenReturn(createdCoupon);

        var actualValue = couponService.create(coupon);

        assertNotNull(actualValue);
        assertEquals(createdCoupon.getId(), actualValue.getId());
    }

    @Test
    public void givenCoupon_whenCodeIsInvalid_thenThrowCouponInvalidException() {
        var expirationDate = LocalDateTime.now().plusDays(1);
        var coupon = new Coupon("AH43H", "Coupon de Natal", 0.5, expirationDate);

        var exception = assertThrows(CouponInvalidException.class, () -> {
            couponService.create(coupon);
        });

        assertEquals(exception.getMessage(), "Código do cupom menor que o tamanho padrão (6).");
    }

    @Test
    public void givenCoupon_whenCodeHasNonAlphanumeric_thenRemoveNonAlphaAndCreate() {
        var expirationDate = LocalDateTime.now().plusDays(1);
        var coupon = new Coupon("AH43H1&$", "Coupon de Natal", 0.5, expirationDate);
        var createdCoupon = new com.coupon.entity.Coupon(1L,"AH43H1", "Cupon de Natal", 0.5, expirationDate);

        when(couponRepository.save(any())).thenReturn(createdCoupon);

        var actualValue = couponService.create(coupon);

        assertNotNull(actualValue);
        assertEquals(createdCoupon.getId(), actualValue.getId());
        assertEquals("AH43H1", actualValue.getCode());
    }
}
