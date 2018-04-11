package interview.leetcode._3xx._38x;

import java.util.LinkedList;

/**
 * @author zzt
 */
public class LongestFilePath_388 {

  public int lengthLongestPath(String input) {
    char[] cs = (input+"\n").toCharArray();
    LinkedList<int[]> q = new LinkedList<>();
    int dirL = 0, max = 0;
    int len = 0, level = 0;
    boolean dir = true;
    for (char c: cs) {
      switch (c) {
        case '\n':
          if (dir) {
            q.push(new int[]{len, level});
            dirL += len;
          } else {
            max = Math.max(dirL+len+level, max);
            dir = true;
          }
          len = 0;
          level = 0;
          break;
        case '\t':
          level++;
          break;
        case '.':
          dir = false;
        default:
          len++;
          while (!q.isEmpty() && q.peek()[1] >= level) {
            dirL -= q.pop()[0];
          }
          assert dirL >= 0;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    LongestFilePath_388 l = new LongestFilePath_388();
    System.out.println(l.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\t.ext"));
    System.out.println(l.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
  }

}
