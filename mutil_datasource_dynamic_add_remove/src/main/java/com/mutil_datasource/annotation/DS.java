package com.mutil_datasource.annotation;

import com.mutil_datasource.constants.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-17:16
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    String value() default DataSourceConstants.DS_KEY_MASTER;
}
