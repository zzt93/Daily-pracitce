package ADT;

import java.util.Iterator;

/**
 * Created by zzt on 4/26/16.
 * <p>
 * Usage:
 */
public class SkipList<T> {

    public void insert(T t) {

    }

    public boolean contain(T t) {
        return false;
    }

    public boolean remove(T t) {
        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
