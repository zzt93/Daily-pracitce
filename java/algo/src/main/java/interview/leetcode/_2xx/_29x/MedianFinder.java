package interview.leetcode._2xx._29x;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzt
 */
public class MedianFinder {

  private final Comparator<Integer> tComparator = Comparator.naturalOrder();
  private PriorityQueue<Integer> min = new PriorityQueue<>();
  private PriorityQueue<Integer> max = new PriorityQueue<>(tComparator.reversed());

  /**
   * initialize your data structure here.
   */
  public MedianFinder() {
  }

  public void addNum(int num) {
    if (!min.isEmpty() && num > min.peek()) {
      min.add(num);
    } else if (!max.isEmpty() && num < max.peek()) {
      max.add(num);
    } else if (min.size() < max.size()) {
      min.add(num);
    } else {
      max.add(num);
    }

    if (min.size() - max.size() > 1) {
      max.add(min.poll());
    } else if (max.size() - min.size() > 1) {
      min.add(max.poll());
    }
  }

  public double findMedian() {
    if (min.size() == max.size()) {
      return (min.peek() + max.peek()) / 2.0;
    } else if (min.size() > max.size()) {
      return min.peek();
    } else {
      return max.peek();
    }
  }

  public static void main(String[] args) {
    test(1,2,3,4,5);
    test(6,5,4,3,2,1);
    test(6,1,2,3,5,4);
  }

  private static void test(int... ns) {
    MedianFinder m = new MedianFinder();
    for (int n : ns) {
      m.addNum(n);
      System.out.println(m.findMedian());
    }
    System.out.println("------");
  }

}
