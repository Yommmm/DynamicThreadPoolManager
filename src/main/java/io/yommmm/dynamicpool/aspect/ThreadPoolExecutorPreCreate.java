package io.yommmm.dynamicpool.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author 85374
 * @date
 */
@Aspect
@Component
public class ThreadPoolExecutorPreCreate {
    @Value("")
    private String aa;

    private static final Map<String, Executor> executorCache = new ConcurrentHashMap<>();

    /*@Around(value = " execution(* java.util.concurrent.ThreadPoolExecutor.ThreadPoolExecutor(..))")
    public void aspect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("创建了一个线程池");
        pjp.proceed();
    }*/

}
