package jcip.cancel.crawler;

import annotation.GuardedBy;
import jcip.cancel.log.Service;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage:
 */
public abstract class WebCrawler implements Service {

    private volatile TrackingExecutor executor;
    @GuardedBy(type = WebCrawler.class, varName = "this")
    private final Set<URL> urls = new HashSet<>();

    protected WebCrawler() {
        executor = new TrackingExecutor(Executors.newCachedThreadPool());
    }

    @Override
    public synchronized void start() {
        for (URL url : urls) {
            submitTask(url);
        }
    }

    private void submitTask(URL url) {
        executor.submit(new CrawTask(url));
    }

    @Override
    public synchronized void stop() throws InterruptedException {
        final List<Runnable> notStart = executor.shutdownNow();
        saveURL(notStart);
        if (executor.awaitTermination(1, TimeUnit.SECONDS)) {
            saveURL(executor.canceledTask());
        }
    }

    private void saveURL(List<Runnable> list) {
        for (Runnable runnable : list) {
            urls.add(((CrawTask) runnable).getUrl());
        }
    }

    public abstract List<URL> processURL(URL url);

    private class CrawTask implements Runnable {
        private final URL url;

        public CrawTask(URL url) {
            this.url = url;
        }

        public URL getUrl() {
            return url;
        }

        @Override
        public void run() {
            for (URL u : processURL(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitTask(u);
            }
        }
    }
}
