package ADT.skipList;

import net.jcip.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/26/16.
 * <p>
 * <li>not allow null</li>
 */
@NotThreadSafe
public class SkipList<T extends Comparable<T>> {

    private static final int MAX_LEVELS = 33;
    public static final int LOWEST_LEVEL = 0;
    private static Random random = new Random(47);

    private SkipListNode<T> head = new SkipListNode<>(MAX_LEVELS, null);
    /**
     * count from 0, index of next array
     */
    private int currentLevel = 0;
    private int size = 0;

    public SkipList() {
        SkipListNode<T> tail = new SkipListNode<>(MAX_LEVELS, null);
        for (int i = 0; i < MAX_LEVELS; i++) {
            head.initNext(i, tail);
        }
    }

    /**
     * insert new element, if already exist, do nothing
     *
     * @param t element
     */
    public void insert(T t) {
        LinkedList<SkipListNode<T>> res = find(t);
        SkipListNode<T> last = res.getLast();
        if (last.compare(t) == 0) {//already have it
            return;
        }
        assert res.size() == currentLevel + 1;
        int levelCount = 1;
        int anInt = random.nextInt();
        for (int i = 0; i < MAX_LEVELS; i++) {
            if ((anInt & 1) == 0) {
                levelCount++;
                anInt >>>= 1;
            } else {
                break;
            }
        }
        currentLevel = Math.max(currentLevel, levelCount - 1);

        SkipListNode<T> node = new SkipListNode<>(levelCount, t);
        // insert node
        int j = 0;
        for (Iterator<SkipListNode<T>> it = res.descendingIterator();
             it.hasNext() && levelCount > 0; levelCount--) {
            SkipListNode<T> prev = it.next();
            prev.insert(node, j++);
        }
        for (int i = levelCount; i > 0; i--) {
            head.insert(node, j++);
        }

        size++;
    }

    /**
     * try to find the specific element and remember
     * the path to the target element
     *
     * @param t target
     *
     * @return the previous element on the every level of target element
     *
     * @implNote if this element is not in the list, have to move to the bottom level;
     * otherwise, not sure. So this method is not suitable for remove
     */
    private LinkedList<SkipListNode<T>> find(T t) {
        SkipListNode<T> now = head;
        LinkedList<SkipListNode<T>> res = new LinkedList<>();
        findInLevel(now, t, currentLevel, res);
        return res;
    }

    private void findInLevel(SkipListNode<T> now, T t, int currentLevel,
                             LinkedList<SkipListNode<T>> res) {
        if (currentLevel < 0) {
            return;
        }
        SkipListNode<T> next = now.next(currentLevel);

        int compare = next.compare(t);
        if (compare == 0) {
            // add found target or previous element
            res.add(next);
        } else if (compare > 0) { // move down if possible
            // add previous one in this level
            res.add(now);
            findInLevel(now, t, currentLevel - 1, res);
        } else {
            // move right if possible
            findInLevel(next, t, currentLevel, res);
        }
    }

    public boolean contain(T t) {
        LinkedList<SkipListNode<T>> nodes = find(t);
        return nodes.getLast().compare(t) == 0;
    }

    public boolean remove(T t) {
        LinkedList<SkipListNode<T>> nodes = findForRemove(t);
        SkipListNode<T> target = nodes.pollLast();
        if (target.compare(t) != 0) {
            return false;
        }
        int levels = target.getLevels();
        assert levels == nodes.size();
        for (SkipListNode<T> node : nodes) {
            node.removeNext(--levels);
        }
        size--;
        updateLevel();
        return true;
    }

    private void updateLevel() {
        int level;
        for (level = currentLevel; level > 0; level--) {
            if (!head.next(level).isEnd()) {
                break;
            }
        }
        currentLevel = level;
    }

    /**
     * find all the element which next element is t and target if exist
     *
     * @param t target
     *
     * @return the list in meeting order, i.e. {@link LinkedList#getLast()} is target
     */
    private LinkedList<SkipListNode<T>> findForRemove(T t) {
        SkipListNode<T> now = head;
        LinkedList<SkipListNode<T>> res = new LinkedList<>();
        findToBottom(now, t, currentLevel, res);
        return res;
    }

    private void findToBottom(SkipListNode<T> now, T t, int currentLevel,
                              LinkedList<SkipListNode<T>> res) {
        if (currentLevel < 0) {
            res.add(now.next(0));
            return; // reach the bottom
        }
        SkipListNode<T> next = now.next(currentLevel);

        int compare = next.compare(t);
        if (compare == 0) {
            // add previous element of target
            res.add(now);
            findToBottom(now, t, currentLevel - 1, res);
        } else if (compare > 0) {
            findToBottom(now, t, currentLevel - 1, res);
        } else { // next < t
            findToBottom(next, t, currentLevel, res);
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return head.next(LOWEST_LEVEL).isEnd();
            }

            @Override
            public T next() {
                return head.next(LOWEST_LEVEL).getContent();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipList:\n");
        for (int level = currentLevel; level >= 0; level--) {
            int width = 2 * size - 1;
            sb.append("head-");
            SkipListNode<T> tmp = head.next(level);
            while (!tmp.isEnd()) {
                sb.append(tmp.getContent()).append('-');
                width -= 2;
                tmp = tmp.next(level);
            }
            while (width > 0) {
                sb.append('-');
                width--;
            }
            sb.append("nil\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = random.ints(100, 0, 1000).boxed().collect(Collectors.toList());
        SkipList<Integer> skipList = new SkipList<>();
        for (Integer i : list) {
            skipList.insert(i);
            assert skipList.contain(i);
        }
        for (Integer i : list) {
            skipList.remove(i);
            assert !skipList.contain(i);
        }
        //        System.out.println(skipList.toString());
    }
}
