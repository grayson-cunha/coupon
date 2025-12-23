package com.coupon.domain;

import com.coupon.common.util.TextUtils;
import com.coupon.exception.CouponInvalidException;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class Coupon {

    @NotEmpty(message = "Code cannot be null or empty")
    private String code;
    @NotNull(message = "Description cannot be null or empty")
    private String description;
    @NotNull
    @DecimalMin(value = "0.5", message = "Min value should be 0.5 or greater")
    private Double discountValue;
    @Future(message = "Expiration date should be in the future")
    private LocalDateTime expirationDate;

    public Coupon(String code, String description, Double discountValue, LocalDateTime expirationDate) {
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String coupon) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void validateCode() {
        if (code.length() < 6)
            throw new CouponInvalidException("Código do cupom menor que o tamanho padrão (6).");

        if (!TextUtils.isAlphanumeric(code)) {
            String cleanedCode = code.replaceAll("[^a-zA-Z0-9]", "");

            if (cleanedCode.length() == 6) this.code = cleanedCode;

            throw new CouponInvalidException("Código do cupom " + cleanedCode + " com tamanho inválido. Tamanho padrão (6)");
        }
    }

    public com.coupon.entity.Coupon toEntity() {
        return new com.coupon.entity.Coupon(this.code, this.description, this.discountValue, this.expirationDate);
    }
}
