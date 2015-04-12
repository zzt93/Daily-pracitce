import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zzt on 2/14/15.
 */
public class Deque<Item> implements Iterable<Item> {

    private class List<Item> implements Iterable<Item> {
        private Node<Item> head, tail;
        private int msize;

        List() {
            tail = new Node<Item>(null, null, null);
            head = new Node<Item>(null, null, tail);
            tail.pre = head;
        }

        @Override
        public Iterator<Item> iterator() {
            return new Iterator<Item>() {
                private Node<Item> h = head;
                @Override
                public boolean hasNext() {
                    return h.next != tail;
                }
                @Override
                public Item next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException("no more elements");
                    }
                    h = h.next;
                    return h.item;
                }
                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        private class Node<Item> {
            private Item item;
            private Node<Item> pre;
            private Node<Item> next;

            Node(Item it, Node<Item> prev, Node<Item> node) {
                this.item = it;
                this.pre = prev;
                next = node;
            }

        }


        public boolean isEmpty() {
            return msize == 0;
        }
        public int size() {
            return msize;
        }
        public void addFirst(Item item) {
            addCheck(item);
            Node<Item> temp = new Node<Item>(item, head, head.next);
            head.next.pre = temp;
            head.next = temp;
            //
            msize++;
        }
        public void addLast(Item item) {
            addCheck(item);
            Node<Item> temp = new Node<Item>(item, tail.pre, tail);
            tail.pre.next = temp;
            tail.pre = temp;
            //
            msize++;
        }
        public Item removeFirst() {
            removeCheck();
            msize--;

            Node<Item> third = head.next.next;
            Node<Item> second = head.next;
            head.next = third;
            third.pre = head;
            return second.item;
        }
        public Item removeLast() {
            removeCheck();
            msize--;

            Node<Item> first = tail.pre.pre;
            Node<Item> second = tail.pre;
            tail.pre = first;
            first.next = tail;
            return second.item;
        }
        private void addCheck(Item item) {
            if (item == null) {
                throw new NullPointerException();
            }

        }

        private void removeCheck() {
            if (isEmpty()) {
                throw new NoSuchElementException("the deque is empty");
            }

        }
    }

    private List<Item> linkedList;

    public Deque() {
        linkedList = new List<Item>();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size();
    }




    public void addFirst(Item item) {
        linkedList.addFirst(item);
    }

    public void addLast(Item item) {
        linkedList.addLast(item);
    }

    public Item removeFirst() {
        return linkedList.removeFirst();
    }

    public Item removeLast() {
        return linkedList.removeLast();
    }


    @Override
    public Iterator<Item> iterator() {
        return linkedList.iterator();
    }

    public static void main(String[] args) {
        Deque<String> stringDeque = new Deque<String>();
        for (int i = 0; i < args.length/2; i++) {
            stringDeque.addFirst(args[i]);
        }
        for (int i = args.length - 1; i >= args.length/2; i--) {
            stringDeque.addLast(args[i]);
        }

    }
}
