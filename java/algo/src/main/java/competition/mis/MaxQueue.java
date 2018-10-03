package competition.mis;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class MaxQueue {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int q = in.nextInt();
    in.nextLine();
    LinkedList<Integer> l = new LinkedList<>();
    TreeMap<Integer, Integer> m = new TreeMap<>();
    boolean reverse = false;
    while (q-- > 0) {
      String s = in.nextLine();
      String[] split = s.split(" ");
      String op = split[0];
      Integer e;
      switch (op) {
        case "push_front":
          e = new Integer(split[1]);
          if (!reverse) {
            l.addFirst(e);
          } else {
            l.addLast(e);
          }
          m.put(e, m.getOrDefault(e, 0) + 1);
          break;
        case "push_back":
          e = new Integer(split[1]);
          if (!reverse) {
            l.addLast(e);
          } else {
            l.addFirst(e);
          }
          m.put(e, m.getOrDefault(e, 0) + 1);
          break;
        case "pop_front":
          if (l.isEmpty()) {
            System.out.println("Error");
            continue;
          }
          if (!reverse) {
            e = l.removeFirst();
          } else {
            e = l.removeLast();
          }
          System.out.println(e);
          if (m.get(e) == 1) m.remove(e);
          else m.put(e, m.get(e)-1);
          break;
        case "pop_back":
          if (l.isEmpty()) {
            System.out.println("Error");
            continue;
          }
          if (!reverse) {
            e = l.removeLast();
          } else {
            e = l.removeFirst();
          }
          System.out.println(e);
          if (m.get(e) == 1) m.remove(e);
          else m.put(e, m.get(e)-1);
          break;
        case "max":
          if (l.isEmpty()) {
            System.out.println("Error");
            continue;
          }
          System.out.println(m.lastKey());
          break;
        case "reverse":
          reverse = !reverse;
          break;
      }
    }
  }
}