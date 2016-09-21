package generic.learn;

import java.util.Collection;
import java.util.List;

/**
 * Created by zzt on 9/21/16.
 * <p>
 * <h3></h3>
 */
public class Container<T extends Comparable<T>> {
    private List<T> list;

    public void swap(int i, int j) {
        final T t = get(i);
        set(i, get(j));
        set(j, t);
    }

    public T get(int i) {
        return list.get(i);
    }

    public void set(int i, T t) {
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

    public static <E> void copyTo(Collection<? extends E> p, Collection<? super E> c) {
        for (E e : p) {
            c.add(e);
        }
    }

    public static void main(String[] args) {
        final Container<Integer> integerContainer = new Container<>();
    }
}
