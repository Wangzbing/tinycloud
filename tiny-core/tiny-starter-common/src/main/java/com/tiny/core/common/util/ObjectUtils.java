package com.tiny.core.common.util;

import java.util.Objects;

public final class ObjectUtils {


    public static boolean equals(Object a, Object b) {
       return Objects.equals(a,b);
    }

    public static boolean deepEquals(Object a, Object b)  {
        return Objects.deepEquals(a,b);
    }

    public static int hashCode(Object o) {
        return Objects.hashCode(o);
    }

    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    public static String toString(Object o) {
        return Objects.toString(o);
    }

    public static String toString(Object o, String nullDefault){
        return  Objects.toString(o,nullDefault);
    }

    public static <T> T requireNonNull(T obj){
        return Objects.requireNonNull(obj);
    }

    public static <T> T requireNonNull(T obj, String message){
        return Objects.requireNonNull(obj,message);
    }

    public static boolean isNull(Object obj){
        return Objects.isNull(obj);
    }

    public static boolean nonNull(Object obj){
        return Objects.nonNull(obj);
    }

    public static String identityToString(final Object object){
        return org.apache.commons.lang3.ObjectUtils.identityToString(object);
    }

    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return org.apache.commons.lang3.ObjectUtils.defaultIfNull(object,defaultValue);
    }

    private ObjectUtils(){}
}
