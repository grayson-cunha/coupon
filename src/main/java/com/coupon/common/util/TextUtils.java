package com.coupon.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    private static final Pattern PATTERN_NON_ALPHNUM_USASCII = Pattern.compile("[a-zA-Z0-9]+");

    public static boolean isAlphanumeric(String str) {
        Matcher matcher = PATTERN_NON_ALPHNUM_USASCII.matcher(str);
        return matcher.find();
    }

}
