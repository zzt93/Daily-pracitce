package jcip.test;

import annotation.GuardedBy;
import annotation.ThreadSafe;

import java.util.concurrent.Semaphore;

/**
 * Created by zzt on 5/13/16.
 * <h3>Usage:</h3>
 * Using a bounded buffer with <b>2 semaphore</b> to coordinate producer and consumer
 * <p>
 * <p>Another way to achieve the coordination is make the whole method ({@link #get()}
 * and {@link #put(Object)} atomic which will always ensure the invariant</p>
 *
 * @see jcip.buildingBlock.BoundedSet
 */
@ThreadSafe
public class BoundedBuffer<E> {

    private Semaphore availableSpace;
    private Semaphore availableItems;

    @GuardedBy(type = BoundedBuffer.class, varName = "this")
    private int putPosition = 0, getPosition = 0;
    @GuardedBy(type = BoundedBuffer.class, varName = "this")
    private Object[] items;

    public BoundedBuffer(int size) {
        availableItems = new Semaphore(0);
        availableSpace = new Semaphore(size);
        items = new Object[size];
    }

    /**
     * Compared with {@link jcip.buildingBlock.BoundedSet}, this class use
     * two semaphore, which reduce the check like
     * {@link jcip.buildingBlock.BoundedSet#add(Object)}
     *
     * @param e element
     *
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException {
        availableSpace.acquire();
        /**
         * if context switch happen here
         */
        doInsert(e);
        availableItems.release();
    }

    private synchronized void doInsert(E e) {
        int i = putPosition;
        items[i] = e;
        putPosition = (++i == items.length) ? 0 : i;
    }

    /**
     * @return element
     *
     * @throws InterruptedException
     * @see jcip.buildingBlock.BoundedSet#remove(Object)
     */
    public E get() throws InterruptedException {
        availableItems.acquire();
        E e = doGet();
        availableSpace.release();
        return e;
    }

    private synchronized E doGet() {
        int i = getPosition;
        E e = (E) items[i];
        /**
         * to invalidate the reference to release memory
         */
        items[i] = null;
        getPosition = (++i == items.length) ? 0 : i;
        return e;
    }

    public boolean isFull() {
        return availableSpace.availablePermits() == 0;
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }
}
