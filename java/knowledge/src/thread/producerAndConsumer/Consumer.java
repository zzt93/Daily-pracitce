package thread.ProducerAndConsumer;

/**
 * Created by zzt on 4/3/15.
 */
public class Consumer implements Runnable {
    private MatchList list;

    public Consumer(MatchList list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (!list.isDone()) {
            System.out.println(list.get());
        }
        System.out.println("the end of consumer");
    }
}
