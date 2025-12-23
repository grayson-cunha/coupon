package com.coupon.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextUtilsTests {

    @Test
    public void givenString_whenContainsSpecialCharacter_thenIsNotAlphanumeric() {
        var text = "AB123#$%%NJ";

        assertTrue(TextUtils.isNonAlphanumeric(text));
    }

    @Test
    public void givenString_whenNotContainsSpecialCharacter_thenIsNotAlphanumeric() {
        var text = "AB123NJ";

        assertFalse(TextUtils.isNonAlphanumeric(text));
    }
}
