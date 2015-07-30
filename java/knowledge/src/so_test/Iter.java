package so_test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zzt on 7/30/15.
 * <p>
 * Description:
 *  Getting Iterator first then modify container will
 *  invalidate the iterator and cause `java.util.ConcurrentModificationException`
 *  for `next()` and `remove()` but not `hasNext()`
 */
public class Iter {
    public static void main(String[] args) {
        add();
        remove();
    }

    private static void remove() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        Iterator<Integer> iterator = arrayList.iterator();
        arrayList.remove(0);
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
    }

    private static void add() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        Iterator<Integer> iterator = arrayList.iterator();
//        iterator.next();
        arrayList.add(2);
//        iterator.remove();
        System.out.println(iterator.hasNext());
//        System.out.println(iterator.next());
    }
}
