package jcip.gui;

import java.util.concurrent.*;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage:
 */
public class MySwingUtility {
    private static final ExecutorService service =
            Executors.newSingleThreadExecutor(new SwingThreadFactory());
    private static Thread edt;

    private static class SwingThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            edt = new Thread(r);
            return edt;
        }
    }

    public boolean isEventDispatchThread() {
        return Thread.currentThread() == edt;
    }

    public void invokeLater(Runnable r) {
        service.submit(r);
    }

    public void invokeAndWait(Runnable r) {
        final Future<?> future = service.submit(r);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
