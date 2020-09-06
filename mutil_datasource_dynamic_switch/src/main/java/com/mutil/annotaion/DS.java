package com.mutil.annotaion;

import com.mutil.constants.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-16:27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface DS {

    //数据源名称
    String value() default DataSourceConstants.DS_KEY_MASTER;
}
