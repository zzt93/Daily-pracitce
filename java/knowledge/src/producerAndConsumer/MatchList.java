package thread.ProducerAndConsumer;

import java.util.ArrayList;

/**
 * Created by zzt on 4/3/15.
 */
public class MatchList {
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private boolean newMatch = false;
    private boolean done;

    public synchronized int get() {
        while (!newMatch) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        newMatch = false;
        notifyAll();
        return list.get(list.size() - 1);
    }

    public synchronized void put(int i) {
        while (newMatch) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        newMatch = true;
        notifyAll();
        list.add(i);
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }
}
