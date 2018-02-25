package basic;

import java.util.Comparator;

/**
 * @author zzt
 */
public class StringCase {

  public static void main(String[] args) {
//    sample();
    find();
  }

  private static void sample() {
    System.out.println("--- jdk compare ---");
    System.out.println("I".compareToIgnoreCase("İ"));
    System.out.println("ı".compareToIgnoreCase("i"));

    System.out.println("--- upper case compare ---");
    UpperCaseComparator upper = new UpperCaseComparator();
    System.out.println(upper.compare("I", "İ"));
    System.out.println(upper.compare("ı", "i"));
    System.out.println("--- lower case compare ---");
    LowerCaseComparator lower = new LowerCaseComparator();
    System.out.println(lower.compare("I", "İ"));
    System.out.println(lower.compare("ı", "i"));
  }

  private static class UpperCaseComparator
      implements Comparator<String>, java.io.Serializable {

    public int compare(String s1, String s2) {
      int n1 = s1.length();
      int n2 = s2.length();
      int min = Math.min(n1, n2);
      for (int i = 0; i < min; i++) {
        char c1 = Character.toUpperCase(s1.charAt(i));
        char c2 = Character.toUpperCase(s2.charAt(i));
        if (c1 != c2) {
          return c1 - c2;
        }
      }
      return n1 - n2;
    }

  }
  private static class LowerCaseComparator
      implements Comparator<String>, java.io.Serializable {

    public int compare(String s1, String s2) {
      int n1 = s1.length();
      int n2 = s2.length();
      int min = Math.min(n1, n2);
      for (int i = 0; i < min; i++) {
        char c1 = Character.toLowerCase(s1.charAt(i));
        char c2 = Character.toLowerCase(s2.charAt(i));
        if (c1 != c2) {
          return c1 - c2;
        }
      }
      return n1 - n2;
    }

  }


  private static void find() {
    int charLimit = 65536;
    for (int i = 0; i < charLimit; i++) {
      for (int j = i+1; j < charLimit; j++) {
        if (Character.toUpperCase(i) == Character.toUpperCase(j)
            && Character.toLowerCase(i) != Character.toLowerCase(j)) {
          System.out.format("same upper case: %c(%d) & %c(%d)\n", ((char) i), i, ((char) j), j);
        }
        if (Character.toLowerCase(i) == Character.toLowerCase(j)
            && Character.toUpperCase(i) != Character.toUpperCase(j)) {
          System.out.format("same lower case: %c(%d) & %c(%d)\n", ((char) i), i, ((char) j), j);
        }
      }
    }
  }
}
