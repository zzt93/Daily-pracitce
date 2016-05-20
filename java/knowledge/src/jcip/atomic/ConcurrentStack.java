package jcip.atomic;

import annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class ConcurrentStack<E> {

    private static class Node<E> {
        private E e;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }
    }

    private AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newNode = new Node<>(item, null);
        Node<E> prev;
        do {
            prev = top.get();
            prev.next = newNode;
        } while (!top.compareAndSet(prev, newNode));
    }

    public E pop() {
        Node<E> newHead;
        Node<E> old;
        do {
            old = top.get();
            if (old == null) {
                return null;
            }
            newHead = old.next;
        } while (!top.compareAndSet(old, newHead));
        return old.e;
    }
}
