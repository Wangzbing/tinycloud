package com.tiny.core.common.util;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.lang.Nullable;

/**
 * @author
 * @date
 * @todo:TODO version:1.0
 */
public final class StringUtils {

    public static boolean isEmpty(String str){
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    public static boolean isEnglish(String value) {
        return value.matches("^[a-zA-Z]*");
    }

    public static String requireNonEmpty(String str, String message) {
        if (isEmpty(str))
            throw new NullPointerException(message);
        return str;
    }

    public static int[] toCodePoints(CharSequence str){
        return  org.apache.commons.lang3.StringUtils.toCodePoints(SystemUtils.getHostName());
    }

    public static boolean isBlank(CharSequence cs){
        return  org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    public static boolean isNotBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
    }

    public static String join(final Object[] array, final String separator){
        return org.apache.commons.lang3.StringUtils.join(array,separator);
    }

    public static boolean isEmpty(String [] strings){
        return strings == null || 0 == strings.length;
    }

    public static boolean isNotEmpty(String [] strings){
        return !isEmpty(strings);
    }

    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        return org.springframework.util.StringUtils.concatenateStringArrays(array1,array2);
    }

    public static String substringAfterLast(String string, final String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(string,separator);
    }

    public static boolean hasText(@Nullable CharSequence str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    /**
     */
    private StringUtils() {}

}
