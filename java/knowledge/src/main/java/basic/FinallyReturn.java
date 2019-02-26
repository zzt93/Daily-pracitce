package basic;

/**
 * @author zzt
 */
public class FinallyReturn {

  public static void main(String[] args) {
    System.out.println(example());
  }

  private static int example() {
    try {
      aFatalMethod();
      return 1;
    } finally {
      System.out.println("Will execute");
      return 2;
    }
  }

  private static int aFatalMethod() {
    throw new RuntimeException();
  }
}
