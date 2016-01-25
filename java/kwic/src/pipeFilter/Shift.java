package pipeFilter;

import java.util.ArrayList;

/**
 * Created by zzt on 1/18/16.
 * <p>
 * Usage:
 */
public class Shift implements Runnable {

    public static final String SPACE = " ";
    QueuePipe shift2Sort;
    QueuePipe input2shift;

    public Shift(QueuePipe input2shift, QueuePipe shift2Sort) {
        this.input2shift = input2shift;
        this.shift2Sort = shift2Sort;
    }

    @Override
    public void run() {
        while (true) {
            StringMessage line;
            try {
                line = input2shift.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            // shift words in line and put them in next pipe
            ArrayList<String> strings = shift(line.getMessage());
            // put it to next pipe
            strings.forEach(s -> {
                try {
                    shift2Sort.put(new SimpleStringMessage(s));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static ArrayList<String> shift(String line) {
        ArrayList<String> res = new ArrayList<>();
        res.add(line);
        String[] split = line.split(SPACE);
        int len = split.length;
        for (int i = 1; i < len; i++) {
            String str = "";
            for (int j = 0; j < len - 1; j++) {
                str += split[(i + j) % len] + SPACE;
            }
            str += split[(i + len - 1) % len];
            res.add(str);
        }
        return res;
    }
}
