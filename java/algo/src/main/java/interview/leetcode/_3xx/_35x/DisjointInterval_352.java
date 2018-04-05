package interview.leetcode._3xx._35x;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author zzt
 */
public class DisjointInterval_352 {

  private TreeSet<Interval> set = new TreeSet<>(Comparator.comparingInt(i -> i.start));

  /**
   * Initialize your data structure here.
   */
  public DisjointInterval_352() {
  }

  public static void main(String[] args) {
    DisjointInterval_352 d = new DisjointInterval_352();
//    int[] a= {1, 3, 7, 2, 6};
    int[] a = new Random().ints(20, 0, 30).toArray();
    for (int i : a) {
      System.out.print(i);
      d.addNum(i);
      System.out.println(d.getIntervals());
    }
  }

  public void addNum(int val) {
    Interval t = new Interval(val, val);
    Interval f = set.floor(t);
    if (f != null && f.end >= val) return;
    boolean end = false, start = false;
    if (f != null && val - 1 == f.end) {
      end = true;
    }
    Interval c = set.ceiling(t);
    if (c != null && c.start <= val) return;
    if (c != null && val + 1 == c.start) {
      start = true;
    }
    if (start && end) {
      f.end = c.end;
      set.remove(c);
    } else if (end) {
      f.end = val;
    } else if (start) {
      c.start = val;
    } else {
      set.add(t);
    }
  }

  public List<Interval> getIntervals() {
    return new ArrayList<>(set);
  }
}

class Interval {

  int start;
  int end;

  Interval() {
    start = 0;
    end = 0;
  }

  Interval(int s, int e) {
    start = s;
    end = e;
  }

  @Override
  public String toString() {
    return "Interval{" +
        "start=" + start +
        ", end=" + end +
        '}';
  }
}