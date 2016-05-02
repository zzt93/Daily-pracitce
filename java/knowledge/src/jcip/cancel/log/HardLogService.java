package jcip.cancel.log;

import annotation.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 4/29/16.
 * <p>
 * Usage:
 */
public class HardLogService implements LogService {

    private final BlockingQueue<String> queue;
    private final PrintWriter writer;
    private final LoggerThread thread;
    @GuardedBy(type = HardLogService.class, varName = "this")
    private volatile boolean stopped;
    @GuardedBy(type = HardLogService.class, varName = "this")
    private volatile int count;

    public HardLogService(BlockingQueue<String> queue, PrintWriter writer, LoggerThread thread) {
        this.queue = queue;
        this.writer = writer;
        this.thread = thread;
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        synchronized (this) {
            stopped = true;
        }
        thread.interrupt();
    }

    @Override
    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            // add synchronization for invariant:
            // if service not stopped, message count must increase
            if (stopped) {
                return;
            }
            count++;
        }
        // should not put blocking method into synchronized, may cause deadlock
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            String msg;
            while (true) {
                try {
                    // check have to at the start: for after interruption, start from here
                    // if after the blocking take, check will be skipped and fail to exit
                    synchronized (this) {
                        if (stopped && count == 0) {
                            break;
                        }
                    }
                    msg = queue.take();
                    writer.printf(msg);
                    synchronized (this) {
                        count--;
                    }
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
