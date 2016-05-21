package jcip.atomic;

import annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zzt on 5/21/16.
 * <p>
 * <h3>Simulation of non-blocking algorithm of queue</h3>
 *
 * @see java.util.concurrent.ConcurrentLinkedQueue#UNSAFE
 * @see java.util.concurrent.ConcurrentLinkedQueue#offer(Object)
 * <p>
 * Difference between library: ConcurrentLinkedQueue uses an ordinary volatile reference
 * and updates it through the reflection-based Unsafe
 */
@ThreadSafe
public class LinkedQueue<E> {

    private static class Node<E> {
        E e;
        /**
         * request for atomic update
         */
        AtomicReference<Node<E>> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = new AtomicReference<>(next);
        }
    }

    private Node<E> empty = new Node<>(null, null);
    private AtomicReference<Node<E>> head
            = new AtomicReference<>(empty);
    private AtomicReference<Node<E>> tail
            = new AtomicReference<>(empty);

    public boolean put(E e) {
        while (true) {
            final Node<E> last = tail.get();
            final Node<E> eNode = last.next.get();
            if (last == tail.get()) {
                if (eNode != null) {
                    tail.compareAndSet(last, eNode);
                } else {
                    final Node<E> aim = new Node<>(e, null);
                    if (last.next.compareAndSet(null, aim)) {
                        tail.compareAndSet(last, aim);
                        return true;
                    }
                }
            }
        }
    }

}
