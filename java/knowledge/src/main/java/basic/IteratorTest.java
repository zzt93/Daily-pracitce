package basic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.TreeSet;

/**
 * @author zzt
 */
public class IteratorTest {

  public static void main(String[] args) {
//    linkedListTest();

    TreeSet<Integer> set = new TreeSet<>(
        Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Spliterator<Integer> spliterator = set.spliterator();
//    Spliterator<Integer> f = spliterator.trySplit();
//    print(spliterator, "old");
//    print(f, "new");
//
//    f = set.spliterator().trySplit();
//    Spliterator<Integer> f2 = f.trySplit();
//    print(f, "new-old");
//    print(f2, "new-new");

    spliterator = set.spliterator();
    spliterator.trySplit();
    Spliterator<Integer> s2 = spliterator.trySplit();
    print(spliterator, "old-old");
    print(s2, "old-new");
  }

  private static void linkedListTest() {
    LinkedList<Integer> l = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    Spliterator<Integer> spliterator = l.spliterator();
    Spliterator<Integer> f = spliterator.trySplit();
    print(spliterator, "old");
    print(f, "new");

    f = l.spliterator().trySplit();
    Spliterator<Integer> f2 = f.trySplit();
    print(f, "new-old");
    print(f2, "new-new");
  }

  private static void print(Spliterator<Integer> spliterator, String name) {
    System.out.println(name);
    spliterator.forEachRemaining(i -> System.out.print(i + ","));
    System.out.println();
  }

}
