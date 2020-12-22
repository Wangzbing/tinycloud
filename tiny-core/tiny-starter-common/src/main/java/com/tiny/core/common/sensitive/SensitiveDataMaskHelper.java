package com.tiny.core.common.sensitive;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author Amin
 */
public class SensitiveDataMaskHelper {

    public static <T> void desensitization(T t) {
        if(t != null){
            doDesensitization(t.getClass(),t);
        }
    }

    private static void doDesensitization(Class<?> clazz, Object object) {

        Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if(String.class.isAssignableFrom(field.getType())){

                try {
                    //如果需要打码
                    SensitiveDataMask annotation = field.getAnnotation(SensitiveDataMask.class);
                    if (annotation != null) {
                        if (field.getType() == String.class) {
                            String fieldValue = (String) field.get(object);
                            int length = fieldValue.length();
                            int totalNoMaskLen = annotation.frontDisplay() + annotation.backDisplay();
                            if (totalNoMaskLen == 0) {
                                fieldValue = fillMask(annotation.mask(), length);
                            }

                            if (totalNoMaskLen < length) {
                                StringBuilder sb = new StringBuilder();
                                for (int j = 0; j < fieldValue.length(); j++) {
                                    if (j < annotation.frontDisplay()) {
                                        sb.append(fieldValue.charAt(j));
                                        continue;
                                    }
                                    if (j > (fieldValue.length() - annotation.backDisplay() - 1)) {
                                        sb.append(fieldValue.charAt(j));
                                        continue;
                                    }
                                    sb.append(annotation.mask());
                                }
                                fieldValue = sb.toString();
                                field.set(object,fieldValue);
                            }
                        }
                    }

                } catch (IllegalAccessException ex) {
                    throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
                }

            }
        }
    }


    private static String fillMask(String mark, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(mark);
        }
        return sb.toString();
    }

    static class DTO{
        @SensitiveDataMask(backDisplay = 1,frontDisplay = 1)
        private long id =  100 ;
        @SensitiveDataMask(backDisplay = 4,frontDisplay = 3)
        private String a = "13880726866";
    }

    public static void main(String[] args) {
        DTO dto = new DTO();
        SensitiveDataMaskHelper.desensitization(dto);
        System.out.println(dto.a);
    }


}
