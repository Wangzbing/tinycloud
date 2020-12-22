package com.tiny.core.common.security;

import org.springframework.util.DigestUtils;

/**
 * @author Amin
 */
public final class MD5Utils {

    private static final String DEFAULT_SLAT = "slat@2020";

    public static String encode(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    public static String encode(String string) {
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }

    public static String encodeWithSlat(String string,String slat){
        String origin  = string +"/"+slat;
        return DigestUtils.md5DigestAsHex(origin .getBytes());
    }

    public static String md5WithSlat(String string) {
        return encodeWithSlat(string, DEFAULT_SLAT);
    }

    private MD5Utils(){}

}
