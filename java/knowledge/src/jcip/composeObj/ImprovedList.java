package jcip.composeObj;

import annotation.GuardedBy;
import annotation.ThreadSafe;

import java.util.HashSet;
import java.util.List;

/**
 * Created by zzt on 4/21/16.
 * <p>
 * "There is a less fragile alternative for adding an atomic operation to an existing
 * class: composition. ImprovedList implements the List operations
 * by delegating them to an underlying List instance, and adds an atomic put-
 * IfAbsent method. (Like Collections.synchronizedList and other collections
 * wrappers, ImprovedList assumes that once a list is passed to its constructor, the
 * client will not use the underlying list directly again, accessing it only through the
 * ImprovedList .)"
 * </p>
 * <p>
 * "ImprovedList adds an additional level of locking using its own intrinsic lock.
 * It does not care whether the underlying List is thread-safe, because it provides
 * its own consistent locking that provides thread safety even if the List is not
 * thread-safe or changes its locking implementation. While the extra layer of syn-
 * chronization may add some small performance penalty, 7 the implementation in
 * ImprovedList is less fragile than attempting to mimic the locking strategy of an-
 * other object."
 * </p>
 */
@ThreadSafe
public abstract class ImprovedList<E> implements List<E> {
    @GuardedBy(type = ImprovedList.class, varName = "this")
    private final List<E> list;

    public ImprovedList(List<E> list) {
        this.list = list;
    }

    public synchronized E putIfAbsent(E e) {
        if (!list.contains(e)) {
            list.add(e);
        }
        return e;
    }
}
