package thread;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by zzt on 10/5/16.
 * <p>
 * <h3></h3>
 */
public class States {

  public static void main(String[] args) throws InterruptedException {
//    test(States::getSleep);
    test(States::getWait);
//    test(States::getIO);
  }

  private static void test(Supplier<Runnable> supplier) throws InterruptedException {
    Thread thread = new Thread(supplier.get());
    System.out.println(thread.getState());
    thread.start();
    Thread.sleep(1000);
    System.out.println(thread.getState());
    Thread.sleep(1000);
    System.out.println(thread.getState());
    thread.interrupt();
    System.out.println(thread.getState());
  }

  private static Runnable getSleep() {
    return () -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }
  private static Runnable getIO() {
    return () -> {
      // blocking read
      try {
        System.in.read();
      } catch (IOException e) {
        throw new AssertionError(e);
      }
    };
  }
  private static Runnable getWait() {
    Object lock = new Object();
    return () -> {
      try {
        synchronized (lock) {
          lock.wait();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }

}
