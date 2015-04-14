package thread.ProducerAndConsumer;

/**
 * Created by zzt on 4/3/15.
 */
public class Test {
    public static void main(String[] args) {
        MatchList matchList = new MatchList();
        Producer producer = new Producer(matchList, "IntegerArray.txt");
        Consumer consumer = new Consumer(matchList);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
