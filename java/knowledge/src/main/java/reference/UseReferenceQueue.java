package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/27/16. <p> Usage: A kind of simulation to the implementation of WeakHashMap
 */
public class UseReferenceQueue {

  private static ReferenceQueue<Object> queue;

  public static void main(String[] args) throws InterruptedException {
    queue = new ReferenceQueue<>();
    ReferenceWithCleanUp<Object, VeryBig> cleanUp
        = new ReferenceWithCleanUp<>(new Object(), queue, new VeryBig("1"));
    for (int i = 0; i < 100; i++) {
      int[] ints = new int[1000000];
    }
    System.out.println(cleanUp.isEnqueued());
    cleanUp();
  }

  public static void cleanUp() throws InterruptedException {
    ReferenceWithCleanUp<Object, VeryBig> ref;
    Object obj;
    TimeUnit.SECONDS.sleep(2);
    while ((obj = queue.poll()) != null) {
      ref = (ReferenceWithCleanUp<Object, VeryBig>) obj;
      ref.cleanUp();
    }
  }
}

class ReferenceWithCleanUp<T, V> extends WeakReference<T> {

  private V v;

  public ReferenceWithCleanUp(T referent, ReferenceQueue<? super T> q, V v) {
    super(referent, q);
    this.v = v;
  }

  public void cleanUp() {
    // clean up resource not handled by GC, i.e. direct buffer
    System.out.println("Cleaning up");
    this.v = null;
  }
}