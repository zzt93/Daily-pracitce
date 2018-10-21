package competition.practice.roundC2018;

import competition.utility.MyIn;
import competition.utility.MyOut;
import java.io.FileNotFoundException;

/**
 * @author zzt
 */
public class Fairies {

  public static void main(String[] args) {
    MyIn in;
    MyOut out = new MyOut("res");
    try {
      in = new MyIn("testCase/.in");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    int trail = in.nextInt();
    long res;
    for (int i = 0; i < trail; i++) {
      res = choose();
      out.println("Case #" + (i + 1) + ": " + res);
      //            out.printf("Case #%d: %.7f\n", (i + 1), res);
    }
  }

  private static long choose() {
    return 0;
  }

}
