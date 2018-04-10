package interview.leetcode._3xx._38x;

/**
 * @author zzt
 */
public class RansomNote_383 {

  public boolean canConstruct(String ransomNote, String magazine) {
    char[] c1 = ransomNote.toCharArray();
    char[] c2 = magazine.toCharArray();
    int[] m1 = new int[256];
    int[] m2 = new int[256];
    for (char c: c1) m1[c]++;
    for (char c: c2) m2[c]++;
    for (int i = 0; i< m1.length; i++) {
      if (m1[i] > m2[i]) return false;
    }
    return true;
  }

}
