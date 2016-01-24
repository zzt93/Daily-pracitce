package pipeFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zzt on 1/18/16.
 * <p>
 * Usage:
 */
public class Sort implements Runnable {

    private final QueuePipe shift2Sort;
    private final QueuePipe sort2output;

    public Sort(QueuePipe shift2Sort, QueuePipe sort2output) {
        this.shift2Sort = shift2Sort;
        this.sort2output = sort2output;
    }


    @Override
    public void run() {
        ArrayList<StringMessage> toSort = new ArrayList<>();
        while (true) {
            StringMessage line = null;
            try {
                line = shift2Sort.poll(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            toSort.add(line);
        }
        Collections.sort(toSort, (o1, o2) -> o1.getMessage().compareTo(o2.getMessage()));
        toSort.forEach(s -> {
            try {
                sort2output.put(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
