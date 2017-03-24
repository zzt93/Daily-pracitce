package jcip.cancel.log;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage:
 */
public interface LogService extends Service {

    void log(String msg) throws InterruptedException;
}
