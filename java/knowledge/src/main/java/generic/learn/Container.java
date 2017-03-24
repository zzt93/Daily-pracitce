package generic.learn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zzt on 9/21/16.
 * <p>
 * <h3></h3>
 */
public class Container<T extends Comparable<? super T>> {
    private List<T> list = new ArrayList<T>();


    public void swap(int i, int j) {
        final T t = get(i);
        set(i, get(j));
        set(j, t);
    }

    public T get(int i) {
        return list.get(i);
    }

    public void add(T t) {
        list.add(t);
    }

    private void set(int i, T t) {
        list.set(i, t);
    }


    public void simpleAddAll(Collection<T> collection) {
        for (T t : collection) {
            list.add(t);
        }
    }

    public <E extends T> void addAll(Collection<E> collection) {
        for (E t : collection) {
            list.add(t);
        }
    }

    public void addAllAgain(Collection<? extends T> collection) {
        for (T t : collection) {
            list.add(t);
        }
    }

    public void copyOut(Collection<? super T> c) {
        for (T t : list) {
            c.add(t);
        }
    }

    public static <E> void copyTo(Collection<? extends E> p, Collection<? super E> c) {
        for (E e : p) {
            c.add(e);
        }
    }

//    public static void swap(List<?> l, int i, int j) {
//        Object t = l.get(i);
//        l.set(i, l.get(j));
//        l.set(j, t);
//    }


    public static void swapAgain(List<Object> l, int i, int j) {
        Object t = l.get(i);
        l.set(i, l.get(j));
        l.set(j, t);
    }

    public static <T> void swap(List<T> l, int i, int j) {
        T t = l.get(i);
        l.set(i, l.get(j));
        l.set(j, t);
    }

    public static void swapAgain(Object[] ts, int i, int j) {
        Object tmp = ts[i];
        ts[i] = ts[j];
        ts[j] = tmp;
    }

    public static <T extends Integer> void swap(T[] ts, int i, int j) {
        T tmp = ts[i];
        ts[i] = ts[j];
        ts[j] = tmp;
    }

    public static <T> void swap(T[] ts, int i, int j) {
        T tmp = ts[i];
        ts[i] = ts[j];
        ts[j] = tmp;
    }

    public static void main(String[] args) {
        genericCast();

        wildSuper();

        chooseWhich();
    }

    private static void chooseWhich() {
        Integer[] integers = new Integer[2];
        swap(integers, 0, 1);
    }

    private static void wildSuper() {
        Container<Sub> s = new Container<>();
    }

    private static void genericCast() {
//        ArrayList<Base> sl = new ArrayList<Sub>();

        final Container<Integer> integerContainer = new Container<>();
        integerContainer.add(1);
        final Integer integer = integerContainer.get(0);
    }
}
