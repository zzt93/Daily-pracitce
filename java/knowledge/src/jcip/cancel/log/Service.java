package jcip.cancel.log;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage:
 */
public interface Service {
    void start();

    void stop() throws InterruptedException;
}
