package competition.leetcode.w75;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zzt
 */
public class RotateString {

  public boolean rotateString(String A, String B) {
    char[] bc = B.toCharArray();
    LinkedList<Character> ac = new LinkedList<>();
    for (char c : A.toCharArray()) {
      ac.add(c);
    }
    int i;
    for (i = 0; i < bc.length; i++) {
      if (equal(bc, ac)) return true;
      Character c = ac.removeFirst();
      ac.addLast(c);
    }
    return false;
  }

  public boolean equal(char[] bc, List<Character> ac) {
    ListIterator<Character> it = ac.listIterator();
    for (char aBc : bc) {
      if (aBc != it.next()) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    RotateString r = new RotateString();
    System.out.println(r.rotateString("abcde", "cdeab"));
    System.out.println(r.rotateString("abcde", "cdeba"));
    System.out.println(r.rotateString("abcabcd", "abcdabc"));
  }
}
