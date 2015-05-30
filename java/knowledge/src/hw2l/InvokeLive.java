package hw2l;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 5/28/15.
 * <p>
 * Description:
 */
public class InvokeLive implements Runnable {
    private int period;
    private BlockingQueue<ArrayList<String>> queue = new ArrayBlockingQueue<>(10);

    public InvokeLive(int period) {
        this.period = period;
    }

    @Override
    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", "Live.py", "" + period);
            Process p = pb.start();
//            File log = new File("test");
//            pb.redirectErrorStream(true);
//            pb.redirectOutput(ProcessBuilder.Redirect.to(log));
//            pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //System.out.println(p.waitFor());
            String line;

            ArrayList<String> res = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                res.add(line);
            }
            queue.put(res);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getLive() throws InterruptedException {
        return queue.take();
    }
}
