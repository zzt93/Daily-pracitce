package com.zzt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InterruptedIOException;

/**
 * Created by zzt on 7/18/16.
 * <p>
 * <h3></h3>
 */
@Aspect
public class LoggingAspect {

    private final RetryStrategy retryStrategy;

    @Autowired
    public LoggingAspect(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    @Before("execution(* com.zzt.learnspring.service.HelloWorldService+.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }


    @Around("execution(* com.zzt.learnspring.service.UpdateService+.*(..))")
    public Object logAfterReturnOrError(ProceedingJoinPoint joinPoint) throws Exception {
        int count = 1;

        while (true) {
            try {
                Object response = joinPoint.proceed();
                final Object[] input = joinPoint.getArgs();
                if (!this.retryStrategy.isRetry(response, count, input)) {
                    return response;
                }

                long ex = this.retryStrategy.getRetryIntervalInMS();
                Thread.sleep(ex);
            } catch (InterruptedException var10) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new Exception(throwable);
            }
            ++count;
        }
    }
}
