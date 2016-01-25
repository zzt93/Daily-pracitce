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

    public static final int TIMEOUT = 3;
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
                // using the timeout to indicate whether it is finished
                // changing to a special StringMessage may be a better way
                // e.g. A StringMessage with null message
                line = shift2Sort.poll(TIMEOUT);
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
