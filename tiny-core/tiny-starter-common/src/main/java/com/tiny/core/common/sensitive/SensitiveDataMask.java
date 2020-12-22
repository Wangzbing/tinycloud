package com.tiny.core.common.sensitive;


import java.lang.annotation.*;

/**
 * @author Amin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Documented
public @interface SensitiveDataMask {

    /**
     * 前置不需要打码的长度
     * @return
     */
    int frontDisplay() default 0;

    /**
     * 后置不需要打码的长度
     * @return
     */
    int backDisplay() default 0;

    /**
     * 用什么打码
     * @return
     */
    String mask() default "*";
}
