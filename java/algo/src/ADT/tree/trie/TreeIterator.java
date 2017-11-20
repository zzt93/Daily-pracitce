package ADT.tree.trie;

/**
 * Created by zzt on 1/27/16.
 * <p>
 * Usage:
 */
public interface TreeIterator<T> {
    boolean hasChild(T data);

    boolean canExit();

    void move(T data);
}
