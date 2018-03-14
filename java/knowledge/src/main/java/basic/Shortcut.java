package basic;

/**
 * @author zzt
 */
public class Shortcut {

  public static void main(String[] args) {
    System.out.println(-1>>>31);
    System.out.println(-1>>31);
    System.out.println("-- shortcut: '&&' ---");
    if (false && shortcut()) {
      System.out.println("impossible");
    }
    System.out.println("-- shortcut: '&' ---");
    if (false & shortcut()) {
      System.out.println("impossible");
    }
  }

  private static boolean shortcut() {
    System.out.println("should not in shortcut");
    return true;
  }

}
