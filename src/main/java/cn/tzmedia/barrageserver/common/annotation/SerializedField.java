package cn.tzmedia.barrageserver.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zero大神 on 2017/11/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializedField {
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

    /**
     * 是否使用拦截，如果是false那么完全不会处理任何逻辑,例如返回值如果是父类，那么任然会返回所有具体类字段,如果是true，otherwise
     * @return
     */
    boolean enable() default true;
}
