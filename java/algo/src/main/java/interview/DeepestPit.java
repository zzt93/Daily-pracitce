package interview;

/**
 * @author zzt
 */
public class DeepestPit {

  public int solution(int[] n) {
    int i = 0, max = -1;
    int l = n.length;
    while (true) {
      while (i+1 < l && n[i] <= n[i+1]) i++;
      if (i+1>=l) break;
      int s = i, mid, end;
      while (i+1 < l && n[i] > n[i+1]) i++;
      if (i+1>=l) break;
      if (n[i] == n[i+1]) continue;
      else mid = i;

      while (i+1<l && n[i] < n[i+1]) i++;
      if (i+1>=l) {
        end = i;
      }
      else end = i;

      max = Math.max(max, Math.min(n[s]-n[mid], n[end]-n[mid]));
    }
    return max;
  }

  public static void main(String[] args) {
    DeepestPit d = new DeepestPit();
    System.out.println(d.solution(new int[]{0,1,2,3,4}));
    System.out.println(d.solution(new int[]{0,}));
    System.out.println(d.solution(new int[]{0, 1, 3, -2, 0, 1, 0, -3, 2, 3}));
    System.out.println(d.solution(new int[]{0, 1, 3, -2, 0, 1, 0, 0, -3, 2, 3}));
    System.out.println(d.solution(new int[]{0, 1, 3, -2, 0, 0, 0, 0, -3, 2, 3}));
    System.out.println(d.solution(new int[]{0, 1, 3, -2, 0, 0, 0, 0, -2, 2, 3}));
    System.out.println(d.solution(new int[]{0, 1, 3, -1, 0, 0, 0, 0, -2, 2, 3}));
  }
}
