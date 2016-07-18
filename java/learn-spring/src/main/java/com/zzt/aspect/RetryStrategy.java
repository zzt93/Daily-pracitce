package com.zzt.aspect;

/**
 * Created by zzt on 7/18/16.
 * <p>
 * <h3></h3>
 */
public interface RetryStrategy {

    int getRetryIntervalInMS();

    boolean isRetry(Object response, int count, Object[] input);
}
