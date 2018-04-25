package interview.leetcode._4xx._40x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzt
 */
public class QueueReconstruction_406 {

  public int[][] reconstructQueue(int[][] people) {
    Comparator<int[]> c1 = Comparator.comparingInt(is->-is[0]);
    Arrays.sort(people, c1.thenComparing(is->is[1]));
    List<int[]> res = new ArrayList<>(people.length);
    for (int[] aPeople : people) {
      res.add(aPeople[1], aPeople);
    }
    return res.toArray(new int[0][]);
  }

  public static void main(String[] args) {
    QueueReconstruction_406 q = new QueueReconstruction_406();
    System.out.println(Arrays.deepToString(q.reconstructQueue(
        new int[][]{new int[]{7, 0}, new int[]{4, 4}, new int[]{7, 1}, new int[]{5, 0},
            new int[]{6, 1}, new int[]{5, 2}})));
  }
}
