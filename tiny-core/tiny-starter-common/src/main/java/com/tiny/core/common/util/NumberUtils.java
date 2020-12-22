package com.tiny.core.common.util;

import java.util.regex.Pattern;


/**
 * @author Amin
 * @date
 */
public final class NumberUtils {
    /**小数点*/
    private static final String DECIMALS="-?[0-9]+.?[0-9]+";
    /**数字*/
    private static final String INTEGER="[0-9]*";
    public static boolean isInteger(String value) {
        return Pattern.compile(INTEGER).matcher(value).matches();
    }

    public static boolean isDecimals(String value) {
        Pattern compile = Pattern.compile(DECIMALS);
        return compile.matcher(value).matches();
    }

    /**
     * 
     */
    private NumberUtils() {}

}
