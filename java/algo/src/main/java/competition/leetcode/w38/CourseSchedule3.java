package competition.leetcode.w38;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 6/25/17.
 * <p>
 * <h3></h3>
 */
public class CourseSchedule3 {

    public int scheduleCourse(int[][] courses) {
        ArrayList<int[]> ints = new ArrayList<>(courses.length);
        Collections.addAll(ints, courses);
        ints.sort(Comparator.comparingInt(c -> c[1]));

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(c -> -c[0]));
        int sum = 0;
        for (int[] c : ints) {
            sum += c[0];
            queue.add(c);
            if (sum > c[1]) {
                int[] poll = queue.poll();
                sum -= poll[0];
            }
        }
        return queue.size();
    }

    public static void main(String[] args) {
        CourseSchedule3 schedule3 = new CourseSchedule3();
        System.out.println(schedule3.scheduleCourse(new int[][]{new int[]{100, 200}, new
                int[]{200, 1300}, new int[]{1000, 1250}, new int[]{2000, 3200}}));
        System.out.println(schedule3.scheduleCourse(new int[][]{new int[]{100, 100}}));
        System.out.println(schedule3.scheduleCourse(new int[][]{new int[]{101, 100}}));
        System.out.println(schedule3.scheduleCourse(new int[][]{new int[]{5, 5}, new int[]{4, 6},
                new int[]{2, 6}}));
    }
}
