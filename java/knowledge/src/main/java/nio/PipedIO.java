package nio;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/11/16.
 * <p>
 * Usage:
 */
class Sender implements Runnable {
    private PipedWriter writer;
    private Random rand = new Random(12);

    public Sender(PipedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    writer.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

class Receiver implements Runnable {
    private PipedReader reader;

    public Receiver(PipedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Read: " + (char) reader.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws Exception {
        PipedWriter writer = new PipedWriter();
        Sender sender = new Sender(writer);
        Receiver receiver = new Receiver(new PipedReader(writer));
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
