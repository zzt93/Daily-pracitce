package jcip.composeObj;

import annotation.GuardedBy;
import annotation.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 4/21/16.
 * rely on the document of synchronizedList which specify lock is list
 */
@ThreadSafe
public class ListHelper<E> {
    @GuardedBy(type = List.class, varName = "list")
    private final List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public E putIfAbsent(E e) {
        synchronized (list) {
            if (!list.contains(e)) {
                list.add(e);
            }
            return e;
        }
    }
}
