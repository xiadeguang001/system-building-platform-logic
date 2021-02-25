package com.haso.common.annotation;


import java.lang.annotation.*;

/**
 * description: 定义一个注解。
 * @version v1.0
 * @author w
 * @date 2018年8月1日下午2:41:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface  JobAnnotation {
    String showName();
}
