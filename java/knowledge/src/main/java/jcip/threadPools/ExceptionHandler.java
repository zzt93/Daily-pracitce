package jcip.threadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 3/17/18.
 *
 * <h3></h3>
 */
public class ExceptionHandler {

  public static void main(String[] args) {
    ExecutorService service = Executors.newCachedThreadPool((r)->{
      Thread thread = new Thread(r);
      thread.setUncaughtExceptionHandler((t, e) -> {
        System.out.println(t.getName());
        e.printStackTrace();
      });
      return thread;
    });
    // submit will not work
    service.execute(() -> {
      System.out.println("run");
      throw new IllegalArgumentException();
    });
    service.submit(() -> {
      System.out.println("run");
      throw new IllegalArgumentException();
    });
  }

}
