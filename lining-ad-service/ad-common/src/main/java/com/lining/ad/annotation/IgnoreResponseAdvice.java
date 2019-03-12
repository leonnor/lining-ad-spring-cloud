package com.lining.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className IgnoreResponseAdvice
 * description 把一些简单的响应不变成统一的响应
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/12 19:23
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
