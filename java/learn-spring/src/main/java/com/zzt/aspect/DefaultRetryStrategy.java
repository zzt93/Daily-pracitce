package com.zzt.aspect;

/**
 * Created by zzt on 7/18/16.
 * <p>
 * <h3></h3>
 */
public class DefaultRetryStrategy implements RetryStrategy {
    private int maxTry;
    private int intervalInMS;

    public DefaultRetryStrategy(int maxTry, int intervalInMS) {
        this.maxTry = maxTry;
        this.intervalInMS = intervalInMS;
    }

    @Override
    public int getRetryIntervalInMS() {
        return intervalInMS;
    }

    @Override
    public boolean isRetry(Object response, int count, Object[] input) {
        Boolean res = ((Boolean) response);
        return (res == null || !res) && count < maxTry;
    }
}
