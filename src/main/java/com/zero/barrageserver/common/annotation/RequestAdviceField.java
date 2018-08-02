package com.zero.barrageserver.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zero大神 on 2017/11/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestAdviceField {

    /**
     * 需要返回的字段
     * @return
     */
    String[] includes() default {};

    /**
     * 需要去除的字段
     * @return
     */
    String[] excludes() default {};

    /**
     * 数据是否需要加密
     * @return
     */
    boolean encode() default true;
}
