package thread.pv;

/**
 * Created by zzt on 7/2/15.
 *
 * Description: Try to solve reader writer problem
 * using semaphore
 *
 * the key point here is: -- file( the resource) is not the same
 * for reader and writer.
 * reader perspective: file is shared
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
             * get the right to use file the first reader come
             */
            filew.P();
        }
        readCountSem.V();
        // read file
        System.out.println("read file");
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
        // write file
        filew.V();
    }
}
