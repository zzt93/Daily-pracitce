package pipeFilter;

/**
 * Created by zzt on 1/24/16.
 * <p>
 * Usage:
 */
public class DoOutput implements Runnable{
    private final QueuePipe sort2output;

    public DoOutput(QueuePipe sort2output) {
        this.sort2output = sort2output;
    }

    @Override
    public void run() {
        while (true) {
            StringMessage take = null;
            try {
                take = sort2output.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println(take.getMessage());
        }
    }
}
