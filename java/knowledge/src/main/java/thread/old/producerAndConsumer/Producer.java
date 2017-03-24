package thread.old.producerAndConsumer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by zzt on 4/3/15.
 * <p>
 * Description:
 */
public class Producer implements Runnable {
    private final MatchList list;
    private String path;

    public Producer(MatchList list, String path) {
        this.list = list;
        this.path = path;
    }

    @Override
    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(new File(path))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        Random random = new Random();

        synchronized (list) {
            while (scanner.hasNext()) {
                list.put(scanner.nextInt());
                //            try {
                //                Thread.sleep(random.nextInt(2000) + 4000);
                //            } catch (InterruptedException e) {
                //                e.printStackTrace();
                //            }
            }
            /* another solution is to add a sentinel
            list.put(Integer.MAX_VALUE);
             */
            list.setDone(true);
        }
        System.out.println("The end of producer");
    }
}
