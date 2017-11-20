package ADT.tree.trie;

/**
 * Created by zzt on 4/7/16.
 * <p>
 * Usage:
 */
public interface MappingIterator<K, V> extends TreeIterator<K> {
    V getValue();
}
