package basic;

/**
 * @author zzt
 */
public class StrEqual {

  private final String a;

  public StrEqual(String a) {
    this.a = a;
  }

  public String getA() {
    return a;
  }

  public static void main(String[] args) {
    StrEqual a1 = new StrEqual("a");
    StrEqual a2 = new StrEqual("a");
    System.out.println(a1.a==a2.a);
  }
}
