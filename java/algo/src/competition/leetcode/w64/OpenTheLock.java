package competition.leetcode.w64;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zzt on 12/24/17.
 * <p>
 * <h3></h3>
 */
public class OpenTheLock {

    private static int[] add = {1, 10, 100, 1000};

    public int openLock(String[] deadends, String target) {
        HashSet<Integer> dead = new HashSet<>();
        for (String deadend : deadends) {
            int i = Integer.parseInt(deadend);
            if (i == 0) {
                return -1;
            }
            dead.add(i);
        }
        int aim = Integer.parseInt(target);
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(null);
        visited.add(0);
        int c = 0;
        while (!queue.isEmpty()) {
            if (queue.peekFirst() == null) {
                c++;
                queue.removeFirst();
                if (!queue.isEmpty()) queue.add(null);
                continue;
            }
            int f = queue.removeFirst();
            if (f == aim) return c;
            if (dead.contains(f)) continue;
            child(queue, f, visited);
        }
        return -1;
    }

    private void child(LinkedList<Integer> queue, int f, HashSet<Integer> visited) {
        int k = 10;
        for (int i = 0; i < 4; i++) {
            int t = f % k / (k / 10);
            if (t == 9) {
                addQueue(visited, queue, f - add[i] * 9);
                addQueue(visited, queue, f - add[i]);
            } else if (t == 0) {
                addQueue(visited, queue, f + add[i] * 9);
                addQueue(visited, queue, f + add[i]);
            } else {
                addQueue(visited, queue, f + add[i]);
                addQueue(visited, queue, f - add[i]);
            }
            k *= 10;
        }
    }

    private void addQueue(HashSet<Integer> visited, LinkedList<Integer> queue, int i) {
        if (visited.contains(i)) {
            return;
        }
        assert i < 10000;
        visited.add(i);
        queue.add(i);
    }

    public static void main(String[] args) {
        OpenTheLock o = new OpenTheLock();
        System.out.println(o.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"},
                "0202"));
        System.out.println(o.openLock(new String[]{"8887", "8889", "8878", "8898", "8788",
                "8988", "7888", "9888"}, "8888"));
        System.out.println(o.openLock(new String[]{"8888"}, "0009"));
        System.out.println(o.openLock(new String[]{"8888"}, "0002"));
        System.out.println(o.openLock(new String[]{"0000"}, "8888"));
    }
}
