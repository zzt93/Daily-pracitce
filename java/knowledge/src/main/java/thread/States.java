package thread;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by zzt on 10/5/16.
 * <p>
 * <h3></h3>
 */
public class States {

  public static void main(String[] args) throws InterruptedException, IOException {
//    test(States::getSleep);
//    test(States::getWait);
//    test(States::getTimedWait);
//    test(States::getLock);
    test(States::getWhile);
//    test(States::getSync);
//    test(States::getIO);
  }

  private static void test(Supplier<Runnable> supplier) throws InterruptedException, IOException {
    System.out.println("-------");
    Thread thread = new Thread(supplier.get());
    System.out.println(thread.getState());
    thread.start();
    Thread.sleep(1000);
    System.out.println(thread.getState());
    Thread.sleep(1000);
    System.out.println(thread.getState());

    thread.interrupt();
    System.in.close();

    System.out.println(thread.getState());
    System.out.println("-------");
  }

  private static Runnable getWhile() {
    return ()-> {
      while (!Thread.interrupted()) {
      }
    };
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
  private static Runnable getTimedWait() {
    Object lock = new Object();
    return () -> {
      try {
        synchronized (lock) {
          lock.wait(12);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }
  private static Runnable getSync() {
    Object lock = new Object();
    new Thread(()-> {
      synchronized (lock) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    return () -> {
        synchronized (lock) {
        }
    };
  }
  private static Runnable getLock() {
    Lock lock = new ReentrantLock();
    lock.lock();
    return () -> {
      try {
        lock.lockInterruptibly();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }

}
