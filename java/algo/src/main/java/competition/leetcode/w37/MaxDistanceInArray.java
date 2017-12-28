package competition.leetcode.w37;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 6/18/17.
 * <p>
 * <h3></h3>
 */
public class MaxDistanceInArray {

    private static class Tmp {
        private int m;
        private int index;

        public Tmp(int m, int index) {
            this.m = m;
            this.index = index;
        }

    }

    public int maxDistance(int[][] arrays) {
        PriorityQueue<Tmp> max = new PriorityQueue<>(
                (t1, t2) -> -Integer.compare(t1.m, t2.m)
        );
        PriorityQueue<Tmp> min = new PriorityQueue<>(Comparator.comparingInt(t -> t.m));
        for (int i = 0; i < arrays.length; i++) {
            int[] array = arrays[i];
            min.add(new Tmp(array[0], i));
            max.add(new Tmp(array[array.length - 1], i));
        }
        Tmp poll = max.poll();
        Tmp minP = min.poll();
        if (poll.index == minP.index) {
            Tmp poll1 = min.poll();
            Tmp maxP = max.poll();
            return Math.max(poll.m - poll1.m, maxP.m - minP.m);
        }
        return poll.m - minP.m;
    }

    public static void main(String[] args) {
        MaxDistanceInArray array = new MaxDistanceInArray();
        System.out.println(array.maxDistance(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5}, new
                int[]{1, 2, 3}}));
    }
}
