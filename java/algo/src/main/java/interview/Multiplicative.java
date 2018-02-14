package interview;

import java.util.Arrays;

/**
 * @author zzt
 */
public class Multiplicative {

  public static final int D = (int) 1e6;
  public static final int MAX = (int) 1e9;

  public int solution(int[] a, int[] b) {
    int res = 0;
    int i = Arrays.binarySearch(a, 1);
    if (i<0) {
      i = -(i + 1);
    } else {
      if (b[i]==0) {
        i++;
      }
    }
    int l = a.length;

    for (int x = i; x < l; x++) {
      int s = x+1, e = l;
      while (s < e) {
        int mid = (s + e) / 2;
        if (mul(a, b, x, mid)) {
          e = mid;
        } else {
          s = mid+1;
        }
      }
      res += (l - e);
    }
    return res > MAX ? MAX : res;
  }

  private boolean mul(int[] in, int[] fr, int f, int s) {
    int addB = fr[f]+fr[s];
    int addA = in[f]+in[s]+addB/D;
    addB = addB%D;
    long add = addA*D + addB;

    long tmp = ((long)in[f]*D+fr[f])*((long)in[s]*D+fr[s])/D;
    return Long.compare(tmp, add)>=0;
//    long mulB = fr[f]*((long)in[s]*D+fr[s]);
//    int mulA = (int) (mulB/D/D);
//    mulB = mulB/D%D;
//
//    long i = in[f] * fr[s];
//    mulB += (i % D);
//    mulA += (in[f] * in[s] + i/D);
//    int compare = Integer.compare(mulA, addA);
//    if (compare!=0)return compare>0;
//    else return Long.compare(mulB, addB)>=0;

  }

  public static void main(String[] args) {
    Multiplicative m = new Multiplicative();
    System.out.println(m.solution(new int[]{0, 0, 2, 2}, new int[]{0, 0, 0, 0}));
    System.out.println(
        m.solution(new int[]{0, 1, 2, 2, 3, 5}, new int[]{500000, 500000, 0, 0, 0, 20000}));
  }

}
