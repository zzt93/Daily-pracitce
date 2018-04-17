package interview.leetcode._3xx._39x;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zzt
 */
public class LongestSubstring_395 {

  public int longestSubstring(String s, int k) {
    return recur(s.toCharArray(), 0, s.length(), k);
  }

  private int recur(char[] cs, int s, int e, int k) {
    if (e - s < k) return 0;
    PriorityQueue<Integer> q = new PriorityQueue<>();
    q.add(s-1); q.add(e);
    for (List l: count(cs, s, e)) {
      if (l.size() < k && l.size() > 0) q.addAll(l);
    }
    if (q.size() == 2) return e - s;
    int max = 0;
    while (q.size() >= 2) {
      max = Math.max(recur(cs, q.poll()+1, q.peek(), k), max);
    }
    return max;
  }

  private List[] count(char[] cs , int s, int e) {
    List[] map = new List[26];
    for (int i = 0; i < 26; i++) map[i] = new ArrayList<>();
    for (int i = s; i < e; i++) {
      map[cs[i]-'a'].add(i);
    }
    return map;
  }

  public static void main(String[] args) {
    LongestSubstring_395 l = new LongestSubstring_395();
    System.out.println(l.longestSubstring("aaabb", 3));
    System.out.println(l.longestSubstring("ababbc", 3));
    System.out.println(l.longestSubstring("ababbc", 2));
  }

}
