package jcip.cancel.crawler;

import jcip.cancel.log.Service;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage: show usage of Poison pills to stop service
 */
public class IndexingService implements Service {

    public static final File POISON = new File("");
    private final BlockingQueue<File> queue;
    private final IndexingThread indexer = new IndexingThread();
    private final CrawlerThread crawler = new CrawlerThread();

    public IndexingService(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void start() {
        crawler.start();
        indexer.start();
    }

    @Override
    public void stop() {
        crawler.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        indexer.join();
    }

    class IndexingThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    final File take = queue.take();
                    if (take == POISON) {
                        break;
                    }
                    index(take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void index(File take) {
        }
    }

    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl();
            } catch (InterruptedException ignored) {
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }

        private void crawl() throws InterruptedException {
        }
    }
}
