package competition.mis;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Meituan {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    List[] m = new List[n+1];
    for (int i = 0; i < n; i++) {
      m[i] = new LinkedList<Integer>();
    }
    for (int i = 0; i < n; i++) {
      int o = s.nextInt(), t = s.nextInt();
      m[o].add(t);
      m[t].add(o);
    }

  }

  public static void numberRange(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt(), k = s.nextInt(), t = s.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = s.nextInt();
    }
    int[] m = new int[100001];
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < k - 1; i++) {
      if (m[a[i]]++ == t - 1) {
        set.add(a[i]);
      }
    }
    int res = 0;
    for (int i = k - 1; i < n; i++) {
      if (i >= k && m[a[i - k]]-- == t) {
        set.remove(a[i - k]);
      }
      if (m[a[i]]++ == t - 1) {
        set.add(a[i]);
      }
      if (!set.isEmpty()) {
        res++;
      }
    }
    System.out.println(res);
  }

}
