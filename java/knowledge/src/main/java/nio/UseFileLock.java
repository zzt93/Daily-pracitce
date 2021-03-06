package nio;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 3/22/16.
 * <p>
 * Usage:
 */
public class UseFileLock {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("file.txt");
        FileLock fl = fos.getChannel().tryLock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(10000);
            fl.release();
            System.out.println("Released Lock");
        } else {
            System.out.println("Fail to acquire lock");
        }
        fos.close();
    }
}
