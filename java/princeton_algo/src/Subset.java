import java.util.Iterator;

/**
 * Created by zzt on 2/14/15.
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length == 1) {
            int size = Integer.parseInt(args[0]);
            RandomizedQueue<String> queue = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                queue.enqueue(StdIn.readString());
            }
            Iterator<String> iterator = queue.iterator();
            for (int i = 0; i < size; i++) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("java Subset N strings...");
        }
    }
}
