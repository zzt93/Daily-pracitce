package interview.leetcode._3xx._38x;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class LexicoNum_386 {

  public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new LinkedList<>();
    int now = 1;
    for (int i = 0; i < n; i++) {
      res.add(now);
      if (now * 10 <= n) {
        now = now * 10;
      } else if (now % 10 == 9 || now >= n) {
        if (now >= n) now = now / 10;
        now++;
        while (now % 10 == 0) {
          now /= 10;
        }
      } else {
        now++;
      }
    }
    return res;
  }

}
