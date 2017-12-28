package ADT.skipList;

/**
 * Created by zzt on 4/30/16.
 * <p>
 * Usage:
 */
public class SkipListNode<T extends Comparable<T>> {

    private int levels;
    private T content;
    private SkipListNode<T>[] next;

    public SkipListNode(int levels, T content) {
        this.levels = levels;
        this.content = content;
        next = new SkipListNode[levels];
    }

    public int compare(T t) {
        if (content == null) {// mean this is tail node
            return 1;
        }
        return content.compareTo(t);
    }

    public SkipListNode<T> next(int level) {
        checkLevel(level);
        return next[level];
    }

    private void checkLevel(int level) {
        assert level >= 0 && level < levels;
    }

    void initNext(int currentLevel, SkipListNode<T> tail) {
        next[currentLevel] = tail;
    }

    void insert(SkipListNode<T> next, int level) {
        next.initNext(level, this.next[level]);
        initNext(level, next);
    }

    public boolean isEnd() {
        return content == null;
    }

    T getContent() {
        return content;
    }

    void removeNext(int level) {
        checkLevel(level);
        next[level] = next(level).next(level);
    }

    int getLevels() {
        return levels;
    }
}
