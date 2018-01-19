package interview.leetcode._2xx._28x;

import java.util.Iterator;

/**
 * @author zzt
 */
public class PeekingIterator implements Iterator<Integer> {

  private final Iterator<Integer> iterator;
  private Integer integer = null;

  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    this.iterator = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    if (integer != null) {
      return integer;
    }
    return integer = iterator.next();
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    if (integer == null) {
      return iterator.next();
    }
    Integer res = this.integer;
    integer = null;
    return res;
  }

  @Override
  public boolean hasNext() {
    return integer != null || iterator.hasNext();
  }

}
