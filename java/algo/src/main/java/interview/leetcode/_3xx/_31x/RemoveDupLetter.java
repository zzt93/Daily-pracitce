package interview.leetcode._3xx._31x;

/**
 * @author zzt
 */
public class RemoveDupLetter {

  public static void main(String[] args) {
    RemoveDupLetter r = new RemoveDupLetter();
    System.out.println("bacd:" + r.removeDuplicateLetters("bcacdca"));
    System.out.println(r.removeDuplicateLetters(""));
    System.out.println("acb:" + r.removeDuplicateLetters("bacb"));
    System.out.println("bca:" + r.removeDuplicateLetters("bcab"));
    System.out.println("bca:" + r.removeDuplicateLetters("bccaab"));
    System.out.println("bca:" + r.removeDuplicateLetters("bcbcaab"));
    System.out.println("acdb:" + r.removeDuplicateLetters("cbacdcbc"));
    System.out.println("abc:" + r.removeDuplicateLetters("bcabc"));
  }


  public String removeDuplicateLetters(String s) {
    char[] cs = s.toCharArray();
    return "";
  }

}
