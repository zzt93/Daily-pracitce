package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @author zzt
 */
public class QueueExample {

  public static void main(String[] args) {
    try {
      ReferenceQueue aReferenceQueue = new ReferenceQueue();
      Object anObject = new Object();
      WeakReference ref = new WeakReference(anObject, aReferenceQueue);
      String extraData = new String("Extra Data");
      HashMap aHashMap = new HashMap();

//Associate extraData (value) with weak reference
// (key) in aHashMap
      aHashMap.put(ref, extraData);

//Check that a reference to an object was created
      System.out.println("*** created ref to some object");
      System.out.println();
      System.out.println("contents of ref: " + ref.get());
      System.out.println();

//Check whether the Reference Object is enqueued
      System.out.println("ref.isEnqueued(): " + ref.isEnqueued());
      System.out.println();

//Clear the strong reference to anObject
      anObject = null;

//Clear the strong reference to extraData
      if (anObject == null) {
        extraData = null;
      }
//Run the garbage collector, and
//Check the reference object's referent
      System.out.println("*** running gc...");
      System.gc();
      System.out.println();
      System.out.println("contents of ref: " + ref.get());
      System.out.println();

//Check whether the reference object is enqueued
      System.out.println("ref.isEnqueued(): " + ref.isEnqueued());
      System.out.println();

//Enqueue the reference object.
//This method returns false
//if the reference object is already enqueued.
      System.out.println("enqueued=" + ref.enqueue());

      System.out.println(aReferenceQueue.poll());
    } catch (Exception e) {
      System.err.println("An exception occurred:");
      e.printStackTrace();
    }
  }

}
