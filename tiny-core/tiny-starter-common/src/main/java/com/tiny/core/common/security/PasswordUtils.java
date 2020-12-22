package com.tiny.core.common.security;

import java.util.Random;

/**
 * @program: framework-parent
 * @description:
 * @author: zlf
 * @Date: 2019-08-26
 */
public final class PasswordUtils {

    private static final Random RANDOM = new Random();
    private static final char SPECIAL_CHARACTER[] =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();

    private PasswordUtils() {
    }

    public static String complexPassword(int length) {
        return newPassword(length < 8 ? 8: length,false,true);
    }

    public static String numericPassword(int length){
        return newPassword(length < 4 ? 4: length,true,false);
    }

    public static String simplePassword(int length){
        return newPassword(length < 6 ? 6: length,false,false);
    }

    private static String newPassword(int length, boolean number,boolean complex) {

        StringBuilder password = new StringBuilder();

        if(complex){
            for (int x = 0; x < length; ++x) {
                password.append(SPECIAL_CHARACTER[RANDOM.nextInt(SPECIAL_CHARACTER.length)]);
            }
            return password.toString();
        }

        //length为几位密码
        for(int i = 0; i < length; i++) {
            //优化相关常量
            boolean beNumber = RANDOM.nextInt(2) % 2 != 0;
            //如果开关打开，则永远输出数字
            if(number){
                beNumber = true;
            }
            //输出字母还是数字
            if(!beNumber) {
                //输出是大写字母还是小写字母
                int temp = RANDOM.nextInt(2) % 2 == 0 ? 65 : 97;
                password.append((char)(RANDOM.nextInt(26) + temp));
            } else {
                password.append(RANDOM.nextInt(10));
            }
        }
        return password.toString();

    }

    public static void main(String[] args) {
        System.out.println(PasswordUtils.numericPassword(6));
        System.out.println("----------------------------------------");
        System.out.println(PasswordUtils.simplePassword(32));
        System.out.println("----------------------------------------");
        System.out.println(PasswordUtils.complexPassword(32));
    }

}
