package jcip.cancel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 4/29/16.
 * <p>
 * Usage:
 */
public class LogService {

    private final BlockingQueue<String> queue
            = new ArrayBlockingQueue<String>(10);
}
