package competition.leetcode.w48;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 9/3/17.
 * <p>
 * <h3></h3>
 */
public class MaxSwap {

    public int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        Comparator<int[]> comparator = Comparator.comparingInt(n -> -n[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator.thenComparingInt(n -> n[1]));
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            queue.add(new int[]{aChar, i});
        }
        int[] head = queue.poll();
        int i = 0;
        while (head[0] == chars[i]) {
            head = queue.poll();
            i++;
            if (queue.isEmpty()) {
                break;
            }
        }
        if (queue.isEmpty()) {
            return num;
        }
        while (head[0] == queue.peek()[0]) {
            head = queue.poll();
        }
        char t = chars[i];
        chars[i] = (char) head[0];
        chars[head[1]] = t;
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        MaxSwap swap = new MaxSwap();
        System.out.println(swap.maximumSwap(988368));
        System.out.println(swap.maximumSwap(98368));
        System.out.println(swap.maximumSwap(93868));
        System.out.println(swap.maximumSwap(938688));
        System.out.println(swap.maximumSwap(2736));
        System.out.println(swap.maximumSwap(9973));
        System.out.println(swap.maximumSwap(9793));
        System.out.println(swap.maximumSwap(9999));
        System.out.println(swap.maximumSwap(2345));
        System.out.println(swap.maximumSwap(2));
    }
}
