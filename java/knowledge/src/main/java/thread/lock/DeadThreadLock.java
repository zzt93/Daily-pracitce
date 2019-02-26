package thread.lock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzt
 */
public class DeadThreadLock {

  public static void main(String[] args) throws IOException {
    Lock lock = new ReentrantLock();
    ExecutorService service = Executors.newCachedThreadPool();
    service.submit(
        () -> {
          Thread.currentThread().setName("HaveLock");
          lock.lock();
          aFatalMethod();
          lock.unlock();
        }
    );
    service.submit(
        () -> {
          Thread.currentThread().setName("WaitingLock");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          lock.lock();
          // do something
          lock.unlock();
        }
    );

    System.in.read();
  }

  private static void aFatalMethod() {
    throw new RuntimeException("Dead");
  }
}
