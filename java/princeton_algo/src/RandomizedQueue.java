
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zzt on 2/14/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private ArrayList<Item> list = new ArrayList<Item>();

    private class ArrayList<Item> implements Iterable<Item> {
        private Item[] list;
        private int capacity;
        private int msize;
        static final int INI = 11;

        ArrayList() {
            list = (Item[]) new Object[INI];
            capacity = INI;
            msize = 0;
        }

        @Override
        public Iterator<Item> iterator() {
            return new Iterator<Item>() {
                private Item[] tmp = (Item[]) new Object[msize];
                private int index = 0;
                {
                    for (int i = 0; i < tmp.length; i++) {
                        tmp[i] = list[i];
                    }
                    StdRandom.shuffle(tmp);
                }
                @Override
                public boolean hasNext() {
                    return index != msize;
                }

                @Override
                public Item next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return tmp[index++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
        public boolean isEmpty() {
            return msize == 0;
        }
        public int size() {
            return msize;
        }

        private void resize(int c) {
            capacity = c;
            Item[] temp = (Item[]) new Object[capacity];
            for (int i = 0; i < msize; i++) {
                temp[i] = list[i];
            }
            list = temp;
        }

        private void enqueueCheck(Item item) {
            if (item == null) {
                throw new NullPointerException();
            }
            if (capacity == msize) {
                resize(capacity*2);
            }
        }
        private void getCheck() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            if (capacity == msize/4) {
                resize(capacity/2);
            }
        }
        public void add(Item item) {
            enqueueCheck(item);
            list[msize++] = item;
        }
        public Item remove(int i) {
            getCheck();
            Item tmp = list[i];
            for (int i1 = 0; i1 < msize; i1++) {
                if (i1 <= i) {
                    continue;
                }
                list[i1-1] = list[i1];
            }
            msize--;
            return tmp;
        }
        public Item get(int i) {
            getCheck();
            return list[i];
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
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
    public void enqueue(Item item) {
        addCheck(item);
        list.add(item);
    }

    public Item dequeue() {
        removeCheck();
        return list.remove(StdRandom.uniform(size()));
    }

    public Item sample() {
        removeCheck();
        return list.get(StdRandom.uniform(size()));
    }


    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.sample();
    }
}
