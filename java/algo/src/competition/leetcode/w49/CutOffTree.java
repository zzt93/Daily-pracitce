package competition.leetcode.w49;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by zzt on 9/10/17.
 * <p>
 * <h3></h3>
 */
public class CutOffTree {

    private static class Cell implements Comparable<Cell> {
        private int h;
        private int x;
        private int y;

        @Override
        public String toString() {
            return "Cell{" +
                    "h=" + h +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (x != cell.x) return false;
            return y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public Cell(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(@NotNull Cell o) {
            return Integer.compare(h, o.h);
        }

        public int dis(Cell aim, int[][] map) {
            if (same(aim)) {
                return 0;
            }
            map[x][y] = -1;
            // bfs from now
            Queue<Cell> queue = new LinkedList<>();
            HashSet<Cell> set = new HashSet<>();
            queue.add(this);
            queue.add(null);
            int depth = 0;
            while (!queue.isEmpty()) {
                Cell poll = queue.poll();
                if (poll == null) {
                    if (queue.isEmpty()) {
                        break;
                    }
                    depth++;
                    queue.add(null);
                    continue;
                }
                if (aim.same(poll)) {
                    return depth;
                }
                if (set.contains(poll)) {
                    continue;
                }
                set.add(poll);
                map[poll.x][poll.y] = depth;
                queue.addAll(surround(poll, map));
            }
            return -1;
        }

        private static List<Cell> surround(Cell now, int[][] map) {
            int x = now.x;
            int y = now.y;
            ArrayList<Cell> res = new ArrayList<>();
            if (x != 0) {
                if (map[x - 1][y] == 0) {
                    res.add(new Cell(0, x - 1, y));
                }
            }
            if (x != map.length - 1) {
                if (map[x + 1][y] == 0) {
                    res.add(new Cell(0, x + 1, y));
                }
            }
            if (y != 0) {
                if (map[x][y - 1] == 0) {
                    res.add(new Cell(0, x, y - 1));
                }
            }
            if (y != map[0].length - 1) {
                if (map[x][y + 1] == 0) {
                    res.add(new Cell(0, x, y + 1));
                }
            }
            return res;
        }

        private boolean same(Cell now) {
            return x == now.x && y == now.y;
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        int rc = forest.size();
        int[][] map = new int[rc][forest.get(0).size()];
        PriorityQueue<Cell> queue = new PriorityQueue<>();
        for (int x = 0; x < rc; x++) {
            List<Integer> row = forest.get(x);
            for (int y = 0; y < row.size(); y++) {
                Integer i = row.get(y);
                map[x][y] = i == 0 ? -1 : 0;
                if (i > 1) {
                    queue.add(new Cell(i, x, y));
                }
            }
        }

        int res = 0;
        Cell now = new Cell(0, 0, 0);
        while (!queue.isEmpty()) {
            Cell poll = queue.poll();
            if (poll.same(now)) {
                continue;
            }
            int dis = now.dis(poll, cloneArray(map));
            if (dis == -1) {
                return -1;
            }
            res += dis;
            now = poll;
        }
        return res;
    }

    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static void main(String[] args) {
        CutOffTree tree = new CutOffTree();
//        tree.cutOffTree(Lists.newArrayList(Lists.newArrayList(1,2,3), Lists.newArrayList(0,0,0), Lists.newArrayList(7,6,5)));
        System.out.println(tree.cutOffTree(Lists.newArrayList(Lists.newArrayList(69438, 55243, 0,
                43779, 5241, 93591, 73380), Lists.newArrayList(847, 49990, 53242, 21837, 89404,
                63929, 48214), Lists.newArrayList(90332, 49751, 0, 3088, 16374, 70121, 25385),
                Lists.newArrayList(14694, 4338, 87873, 86281, 5204, 84169, 5024), Lists
                        .newArrayList(31711, 47313, 1885, 28332, 11646, 42583, 31460), Lists
                        .newArrayList(59845, 94855, 29286, 53221, 9803, 41305, 60749), Lists
                        .newArrayList(95077, 50343, 27947, 92852, 0, 0, 19731), Lists
                        .newArrayList(86158, 63553, 56822, 90251, 0, 23826, 17478), Lists
                        .newArrayList(60387, 23279, 78048, 78835, 5310, 99720, 0), Lists
                        .newArrayList(74799, 48845, 60658, 29773, 96129, 90443, 14391), Lists
                        .newArrayList(65448, 63358, 78089, 93914, 7931, 68804, 72633), Lists
                        .newArrayList(93431, 90868, 55280, 30860, 59354, 62083, 47669), Lists
                        .newArrayList(81064, 93220, 22386, 22341, 95485, 20696, 13436), Lists
                        .newArrayList(50083, 0, 89399, 43882, 0, 13593, 27847), Lists
                        .newArrayList(0, 12256, 33652, 69301, 73395, 93440, 0), Lists
                        .newArrayList(42818, 87197, 81249, 33936, 7027, 5744, 64710), Lists
                        .newArrayList(35843, 0, 99746, 52442, 17494, 49407, 63016), Lists
                        .newArrayList(86042, 44524, 0, 0, 26787, 97651, 28572), Lists
                        .newArrayList(54183, 83466, 96754, 89861, 84143, 13413, 72921), Lists
                        .newArrayList(89405, 52305, 39907, 27366, 14603, 0, 14104), Lists
                        .newArrayList(70909, 61104, 70236, 30365, 0, 30944, 98378), Lists
                        .newArrayList(20124, 87188, 6515, 98319, 78146, 99325, 88919), Lists
                        .newArrayList(89669, 0, 64218, 85795, 2449, 48939, 12869), Lists
                        .newArrayList(93539, 28909, 90973, 77642, 0, 72170, 98359), Lists
                        .newArrayList(88628, 16422, 80512, 0, 38651, 50854, 55768), Lists
                        .newArrayList(13639, 2889, 74835, 80416, 26051, 78859, 25721), Lists
                        .newArrayList(90182, 23154, 16586, 0, 27459, 3272, 84893), Lists
                        .newArrayList(2480, 33654, 87321, 93272, 93079, 0, 38394), Lists
                        .newArrayList(34676, 72427, 95024, 12240, 72012, 0, 57763), Lists
                        .newArrayList(97957, 56, 83817, 45472, 0, 24087, 90245), Lists
                        .newArrayList(32056, 0, 92049, 21380, 4980, 38458, 3490), Lists
                        .newArrayList(21509, 76628, 0, 90430, 10113, 76264, 45840), Lists
                        .newArrayList(97192, 58807, 74165, 65921, 45726, 47265, 56084), Lists
                        .newArrayList(16276, 27751, 37985, 47944, 54895, 80706, 2372), Lists
                        .newArrayList(28438, 53073, 0, 67255, 38416, 63354, 69262), Lists
                        .newArrayList(23926, 75497, 91347, 58436, 73946, 39565, 10841), Lists
                        .newArrayList(34372, 69647, 44093, 62680, 32424, 69858, 68719), Lists
                        .newArrayList(24425, 4014, 94871, 1031, 99852, 88692, 31503), Lists
                        .newArrayList(24475, 12295, 33326, 37771, 37883, 74568, 25163), Lists
                        .newArrayList(0, 18411, 88185, 60924, 29028, 69789, 0), Lists
                        .newArrayList(34697, 75631, 7636, 16190, 60178, 39082, 7052), Lists
                        .newArrayList(24876, 9570, 53630, 98605, 22331, 79320, 88317), Lists.newArrayList(27204, 89103, 15221, 91346, 35428, 94251, 62745), Lists.newArrayList(26636, 28759, 12998, 58412, 38113, 14678, 0), Lists.newArrayList(80871, 79706, 45325, 3861, 12504, 0, 4872), Lists.newArrayList(79662, 15626, 995, 80546, 64775, 0, 68820), Lists.newArrayList(25160, 82123, 81706, 21494, 92958, 33594, 5243))));
        System.out.println(tree.cutOffTree(Lists.newArrayList(Lists.newArrayList(1, 2, 3), Lists
                .newArrayList(4, 0, 0), Lists.newArrayList(7, 6, 5))));
        System.out.println(tree.cutOffTree(Lists.newArrayList(Lists.newArrayList(1, 2, 3), Lists
                .newArrayList(0, 0, 4), Lists.newArrayList(7, 6, 5))));
        System.out.println(tree.cutOffTree(Lists.newArrayList(Lists.newArrayList(1, 2, 3), Lists
                .newArrayList(0, 0, 0), Lists.newArrayList(7, 6, 5))));
    }
}
