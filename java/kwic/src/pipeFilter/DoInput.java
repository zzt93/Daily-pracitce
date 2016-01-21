package pipeFilter;

import utility.MyIn;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class DoInput implements Runnable{
    MyIn in;
    QueuePipe input2shift;

    public DoInput(QueuePipe input2shift) throws FileNotFoundException {
        in = new MyIn("test");
        this.input2shift = input2shift;
    }


    @Override
    public void run() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            try {
                input2shift.put(line);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
