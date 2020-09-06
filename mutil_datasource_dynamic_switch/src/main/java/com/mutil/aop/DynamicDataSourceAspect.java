package com.mutil.aop;

import com.mutil.annotaion.DS;
import com.mutil.config.DynamicDataSource;
import com.mutil.context.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-16:29
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    //切入点
    @Pointcut("@annotation(com.mutil.annotaion.DS)")//使用 annotation 指定注解
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")//注解 Around 使用环绕通知处理，使用上下文进行对使用注解 DS 的值进行数据源切换，处理完后，恢复数据源。
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();
        DynamicDataSourceContextHolder.setContextKey(dsKey);
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();//最终释放数据源
        }
    }

    //获取DS注解标注的类或方法
    private DS getDSAnnotation(ProceedingJoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DS ds = targetClass.getAnnotation(DS.class);
        // 先判断类的注解，再判断方法注解
        if (Objects.nonNull(ds)) {
            return ds;
        } else {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(DS.class);
        }
    }
}
