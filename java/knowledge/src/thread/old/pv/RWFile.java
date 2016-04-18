package thread.old.pv;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 7/2/15.
 *
 * Description: Try to solve reader writer problem
 * using semaphore
 *
 * the key point here is: -- file( the resource) is not the same
 * for reader and writer.
 *
 * reader perspective: file is shared -- no limit, but using
 *                      the mutex of writer to stop writer
 * writer perspective: file is exclusively used -- a mutex
 *
 */
public class RWFile {
    private Semaphore filew = new Semaphore(1);
    private Semaphore readCountSem = new Semaphore(1);
    private int readCount = 0;

    public void read_i() {
        readCountSem.P();
        readCount++;
        if (readCount == 1) {
            /**
             * get the right to use file at the first reader come
             */
            filew.P();
        }
        readCountSem.V();
        // read file
        System.out.println("read file");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // read file
        readCountSem.P();
        readCount--;
        if (readCount == 0) {
            filew.V();
        }
        readCountSem.V();
    }

    public void write_i() {
        filew.P();
        // write file
        System.out.println("write file");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // write file
        filew.V();
    }

    public void test() {
        Random random = new Random(47);
        final int nThreads = random.nextInt(3) + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        final int readT = random.nextInt(3);
        for (int i = 0; i < (nThreads - readT); i++) {
            executorService.execute(() -> {
                while (true) {
                    write_i();
                }
            });
        }
        for (int i = 0; i < readT; i++) {
            executorService.execute(() -> {
                while (true) {
                    read_i();
                }
            });
        }

    }

    public static void main(String[] args) {
        new RWFile().test();
    }
}
