package interview;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author zzt
 */
public class PDD {

  public static void m(String[] args) {
    Scanner s = new Scanner(System.in);
    String line = s.nextLine();
    char[] cs = line.toCharArray();
    if (cs.length < 2) {
      System.out.println(cs.length);
    }
    int[] m = new int[26];
    for (char c : cs) {
      m[c - 'a']++;
    }
    int m1 = 0, m2 = 1;
    if (m[m1] < m[m2]) {
      m1 = 1;
      m2 = 0;
    }
    for (int i = 2; i < m.length; i++) {
      if (m[i] > m[m1]) {
        m2 = m1;
        m1 = i;
      } else if (m[i] > m[m2]) {
        m2 = i;
      }
    }

    m[m1] = m[m1] + m[m2];
    m[m2] = 0;
    int res = 0;
    for (int aM : m) {
      res += aM * aM;
    }
    System.out.println(res);
  }

  public static void version(String[] args) {
    Scanner s = new Scanner(System.in);
    String v1 = s.nextLine(), v2 = s.nextLine();
    String[] s1 = v1.split("\\.");
    String[] s2 = v2.split("\\.");
    for (int i = 0; i < s1.length && i < s2.length; i++) {
      if (Integer.parseInt(s1[i]) < Integer.parseInt(s2[i])) {
        System.out.println(-1);
        return;
      } else if (Integer.parseInt(s1[i]) > Integer.parseInt(s2[i])) {
        System.out.println(1);
        return;
      }
    }
    System.out.println(Integer.compare(s1.length, s2.length));
  }

  public static void pairOfAB(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt(), k = s.nextInt();
    int l = n / 2, r = n - l;
    if (k > l * r) {
      System.out.println("-");
    } else if (k == l * r) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < Math.max(l, r); i++) {
        sb.append("A");
      }
      for (int i = 0; i < Math.min(l, r); i++) {
        sb.append("B");
      }
      System.out.println(sb);
    } else {

    }
  }

  public static void makeSameHeight(String[] args) {
    Scanner s = new Scanner(System.in);
    int m = s.nextInt(), n = s.nextInt();
    int[] c = new int[4001];
    int maxH = 0;
    PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int i = 0; i < m; i++) {
      int h = s.nextInt();
      q.add(h);
      c[h]++;
      maxH = Math.max(maxH, h);
    }
    int h = 1; // h is exclusive
    int res = Integer.MAX_VALUE, sum = 0, cost = 0;
    while (h <= maxH) {
      for (; sum < n && h <= maxH; h++) {
        cost += sum;
        sum += c[h];
      }
      while (sum > n) {
        cost -= (h - q.poll() - 1);
        sum--;
      }
      res = Math.min(res, cost);
      cost -= (h - q.poll() - 1);
      sum--;
    }
    System.out.println(res);
  }

  public static void main(String[] args) {
    pairOfAB(args);
  }
}
