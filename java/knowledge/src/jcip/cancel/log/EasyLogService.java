package jcip.cancel.log;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage:
 */
public class EasyLogService implements LogService {
    private ExecutorService service = Executors.newCachedThreadPool();
    private final PrintWriter writer;

    public EasyLogService(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @Override
    public void log(String msg) throws InterruptedException {
        service.execute(() -> writer.println(msg));
    }
}
