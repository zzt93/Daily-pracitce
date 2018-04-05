package competition.leetcode.w77;

import java.util.HashSet;

/**
 * @author zzt
 */
public class MorseCode {

  static String[] ss = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
  public int uniqueMorseRepresentations(String[] words) {
    HashSet<String> res = new HashSet<>();
    for (String w: words) {
      res.add(trans(w));
    }
    return res.size();
  }

  private String trans(String w) {
    char[] cs = w.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (char c: cs) {
      sb.append(ss[c-'a']);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    MorseCode m = new MorseCode();
    System.out.println(m.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
  }

}
