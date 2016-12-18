package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by zzt on 12/17/16.
 * <p>
 * <h3></h3>
 */
public class WaitContent {

  private static volatile boolean ready = false;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService service = Executors.newCachedThreadPool();

    testArray(randomArgs(5), service);
    testList(random.ints(5).boxed()
            .collect(Collectors.toCollection(ArrayList<Integer>::new)), service);
  }

  private static Random random = new Random(12);

  private static Integer[] randomArgs(int n) {
    final Integer[] res = new Integer[n];
    for (int i = 0; i < res.length; i++) {
      res[i] = random.nextInt();
    }
    return res;
  }

  private static void testArray(Integer[] args, ExecutorService service) throws InterruptedException {
    // hold lock of args
    service.submit(() -> {
      synchronized (args) {
        while (!ready) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("sleeping1");
        }
      }
    });

    TimeUnit.SECONDS.sleep(2);
    // try to access content of args
    service.submit(() -> {
      synchronized (args[0]) {
        System.out.println(args[0]);
      }
    });
  }

  private static void testList(List<Integer> args, ExecutorService service) throws InterruptedException {
    // hold lock of args
    service.submit(() -> {
      synchronized (args) {
        while (!ready) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("sleeping2");
        }
      }
    });

    TimeUnit.SECONDS.sleep(2);
    // try to access content of args
    service.submit(() -> {
      synchronized (args.get(0)) {
        System.out.println(args.get(0));
      }
    });
  }
}
