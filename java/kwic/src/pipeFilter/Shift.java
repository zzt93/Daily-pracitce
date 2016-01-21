package pipeFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by zzt on 1/18/16.
 * <p>
 * Usage:
 */
public class Shift implements Runnable{

    QueuePipe shift2Sort;
    QueuePipe input2shift;

    public Shift(QueuePipe input2shift, QueuePipe shift2Sort) {
        this.input2shift = input2shift;
        this.shift2Sort = shift2Sort;
    }

    @Override
    public void run() {
        String line = input2shift.peek();
        while (line != null) {
            try {
                line = input2shift.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // shift words in line and put them in next pipe
            ArrayList<String> strings = shift(line);
            //
            line = input2shift.peek();
        }
    }

    private ArrayList<String> shift(String line) {
        ArrayList<String> res = new ArrayList<>();
        res.add(line);

        return res;
    }
}
