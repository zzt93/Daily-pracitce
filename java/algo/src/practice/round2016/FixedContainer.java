package practice.round2016;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Created by zzt on 9/28/15.
 * <p>
 * Description:
 */
public class FixedContainer<T> {
    ArrayList<T> lists;
    private int size;

    public FixedContainer(int size, T t) {
        this.size = size;
        lists = new ArrayList<>(Collections.nCopies(size, t));
    }

    public FixedContainer(FixedContainer<T> integerFixedContainer) {
        this.size = integerFixedContainer.size;
        lists = new ArrayList<>(integerFixedContainer.lists);
    }

    public void trimToSize() {
        lists.trimToSize();
    }

    public int lastIndexOf(Object o) {
        return lists.lastIndexOf(o);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return lists.subList(fromIndex, toIndex);
    }

    public Stream<T> parallelStream() {
        return lists.parallelStream();
    }

    public boolean contains(Object o) {
        return lists.contains(o);
    }

    public void add(int index, T element) {
        if (lists.size() == size) {
            return;
        }
        lists.add(index, element);
    }

    public ListIterator<T> listIterator() {
        return lists.listIterator();
    }

    public boolean containsAll(Collection<?> c) {
        return lists.containsAll(c);
    }

    public T get(int index) {
        return lists.get(index);
    }

    public void clear() {
        lists.clear();
    }

    public boolean addAll(Collection<? extends T> c) {
        return lists.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return lists.addAll(index, c);
    }

    public Stream<T> stream() {
        return lists.stream();
    }

    public Object[] toArray() {
        return lists.toArray();
    }

    public <T1> T1[] toArray(T1[] a) {
        return lists.toArray(a);
    }

    public Spliterator<T> spliterator() {
        return lists.spliterator();
    }

    public boolean removeIf(Predicate<? super T> filter) {
        return lists.removeIf(filter);
    }

    public boolean add(T t) {
        if (lists.size() == size) {
            return false;
        }
        return lists.add(t);
    }

    public Iterator<T> iterator() {
        return lists.iterator();
    }

    public void forEach(Consumer<? super T> action) {
        lists.forEach(action);
    }

    public boolean isEmpty() {
        return lists.isEmpty();
    }

    public int size() {
        return lists.size();
    }

    public ListIterator<T> listIterator(int index) {
        return lists.listIterator(index);
    }

    public T remove(int index) {
        return lists.remove(index);
    }

    public void sort(Comparator<? super T> c) {
        lists.sort(c);
    }

    public int indexOf(Object o) {
        return lists.indexOf(o);
    }

    public void ensureCapacity(int minCapacity) {
        lists.ensureCapacity(minCapacity);
    }

    public boolean removeAll(Collection<?> c) {
        return lists.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return lists.retainAll(c);
    }

    public T set(int index, T element) {
        return lists.set(index, element);
    }

    public boolean remove(Object o) {
        return lists.remove(o);
    }

    public void replaceAll(UnaryOperator<T> operator) {
        lists.replaceAll(operator);
    }
}
