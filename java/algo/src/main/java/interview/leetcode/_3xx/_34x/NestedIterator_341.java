package interview.leetcode._3xx._34x;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

interface NestedInteger {


  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();


  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();


  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();

}

/**
 * @author zzt
 */
public class NestedIterator_341 implements Iterator<Integer> {

  private Iterator<Integer> it;

  public NestedIterator_341(List<NestedInteger> nestedList) {
    LinkedList<Integer> list = new LinkedList<>();
    add(list, nestedList);
    it = list.iterator();
  }

  private void add(List<Integer> list, List<NestedInteger> nested) {
    for (NestedInteger ni : nested) {
      if (ni.isInteger()) {
        list.add(ni.getInteger());
      } else {
        add(list, ni.getList());
      }
    }
  }

  @Override
  public Integer next() {
    return it.next();
  }

  @Override
  public boolean hasNext() {
    return it.hasNext();
  }
}