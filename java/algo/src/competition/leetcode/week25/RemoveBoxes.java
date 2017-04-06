package competition.leetcode.week25;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class RemoveBoxes {

    private static final int MAX = 102;

    private static class Color {
        private int color;
        private int count = 1;

        public Color(int color) {
            this.color = color;
        }

        void inc() {
            count++;
        }

        int getCountSquare(int otherLen) {
            int i = count + otherLen;
            return i * i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Color color1 = (Color) o;

            return color == color1.color;
        }

        @Override
        public int hashCode() {
            return color;
        }
    }

    public int removeBoxes(int[] boxes) {
        List<Color> list = new ArrayList<>(boxes.length);
        int[][][] dp = new int[MAX][][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[MAX][];
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = new int[MAX];
                for (int k = 0; k < dp[i][j].length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        list.add(new Color(boxes[0]));
        int last = boxes[0];
        for (int i = 1; i < boxes.length; i++) {
            if (last == boxes[i]) {
                list.get(list.size() - 1).inc();
            } else {
                list.add(new Color(boxes[i]));
                last = boxes[i];
            }
        }

        return solve(dp, 0, list.size(), 0, list);
    }

    private int solve(int[][][] dp, int start, int end, int otherLen, List<Color> list) {
        if (dp[start][end][otherLen] > -1) {
            return dp[start][end][otherLen];
        }
        if (start == end && otherLen == 0) {
            return dp[start][end][otherLen] = 0;
        } else {
            Color last = list.get(end - 1);
            dp[start][end][otherLen] = solve(dp, start, end - 1, 0, list) + last.getCountSquare(otherLen);

            for (int i = start; i < end - 1; i++) {
                if (last.equals(list.get(i))) {
                    int tmp = solve(dp, start, i + 1, otherLen + last.count, list)
                            + solve(dp, i + 1, end - 1, 0, list);
                    if (tmp > dp[start][end][otherLen]) {
                        dp[start][end][otherLen] = tmp;
                    }
                }
            }
        }
        return dp[start][end][otherLen];
    }

    public static void main(String[] args) {
        RemoveBoxes removeBoxes = new RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(new int[]{1}));
        System.out.println(removeBoxes.removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }
}
