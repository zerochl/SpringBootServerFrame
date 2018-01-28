package cn.tzmedia.barrageserver.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpApi {
    String value() default "";//通过key获得配置文件中的值
    Class[] interceptor() default {};
}
