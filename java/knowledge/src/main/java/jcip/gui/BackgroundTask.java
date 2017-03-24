package jcip.gui;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage: somewhat a simulation of the {@link SwingWorker} from jcip
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final Computation computation = new Computation();

    private class Computation extends FutureTask<V> {

        public Computation() {
            super(BackgroundTask.this::compute);
        }

        @Override
        protected void done() {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    V res = null;
                    Throwable throwable = null;
                    boolean cancelled = false;
                    try {
                        res = get();
                    } catch (InterruptedException e) {
                        cancelled = true;
                    } catch (ExecutionException e) {
                        throwable = e;
                    } finally {
                        onCompletion(res, throwable, cancelled);
                    }
                });
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract V compute() throws Exception;

    public void onCompletion(V result, Throwable throwable, boolean cancelled) {
    }

    public void onProgress(int current, int max) {
    }
}
