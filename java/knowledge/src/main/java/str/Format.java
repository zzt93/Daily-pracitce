package str;

/**
 * @author zzt
 */
public class Format {

  public static void main(String[] args) {
    System.out.format("%04x\n", 15);
    System.out.format("%-4x\n", 15);
    System.out.format("%#x\n", 15);
    System.out.format("% d\n", 15);
    System.out.format("%,14d\n", 15000000);
    System.out.format("%(d\n", -15);
  }

}
