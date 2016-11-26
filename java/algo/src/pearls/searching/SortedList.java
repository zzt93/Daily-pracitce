package pearls.searching;

/**
 * Created by zzt on 11/23/16.
 * <p>
 * <h3></h3>
 */
public interface SortedList {
    void insert(int t);

    @Override
    String toString();

    default void recursiveInsert(int i) {
    }
}
