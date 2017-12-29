package interview.leetcode._2xx._22x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzt
 */
public class SummaryRanges {

  private final static int START = 0;
  private final static int IN = 1;
  private final static int END = 2;

  public static void main(String[] args) {
    SummaryRanges s = new SummaryRanges();
    System.out.println(s.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    System.out.println(s.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
  }

  public List<String> summaryRanges(int[] n) {
    if (n.length==0) {
      return Collections.emptyList();
    }
    List<String> res = new ArrayList<>();
    int[] now = new int[2];
    int state = START;
    int l = n.length;
    for (int i = 0; i < l - 1; i++) {
      switch (state) {
        case START:
          if (n[i] == n[i + 1] - 1) {
            now[0] = n[i];
            state = IN;
          } else {
            res.add("" + n[i]);
          }
          break;
        case IN:
          if (n[i] != n[i + 1] - 1) {
            state = END;
            i--;
          }
          break;
        case END:
          now[1] = n[i];
          res.add("" + now[0] + "->" + now[1]);
          state = START;
          break;
      }
    }
    switch (state) {
      case END:
      case IN:
        now[1] = n[l - 1];
        res.add("" + now[0] + "->" + now[1]);
        break;
      case START:
        res.add("" + n[l - 1]);
        break;
    }
    return res;
  }

}
