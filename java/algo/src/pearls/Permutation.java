package pearls;

import competition.utility.Swap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/23/16.
 * <p>
 * Usage:
 */
public class Permutation {
    public static void AnmStdout(ArrayList<Integer> list, int m) {
        Anm(list, m, m);
    }

    private static void Anm(ArrayList<Integer> list, int aim, int remaining) {
        if (remaining == 0) {
            System.out.println(list.subList(0, aim));
        }
        int now = aim - remaining;
        for (int i = now; i < list.size(); i++) {
            // choose every possible element to now position
            Swap.swap(list, i, now);
            Anm(list, aim, remaining - 1);
            Swap.swap(list, i, now);
        }
    }

    public static void CnmStdout(ArrayList<Integer> list, int m) {
        //        for (int start = 0; start < list.size(); start++) {
        //            int next = start + 1;
        //            for (int j = next; j < list.size() && start + m < list.size(); j++) {
        //                Swap.swap(list, next, j);
        //                System.out.println(list.subList(start, start + m));
        //                Swap.swap(list, next, j);
        //            }
        //        }
        for (int i = 0; i + m <= list.size(); i++) {
            Cnm(list, i, m, m);
        }
    }

    private static void Cnm(ArrayList<Integer> list, int start, int aim, int remaining) {
        if (remaining == 0) {
            System.out.println(list.subList(start, start + aim));
        }
        int now = aim - remaining + start + 1;
        for (int i = now; i < list.size(); i++) {
            Swap.swap(list, i, now);
            Cnm(list, start, aim, remaining - 1);
            Swap.swap(list, i, now);
        }
    }

    public static void main(String[] args) {
        List<Integer> test = new Random(47).ints(4, 0, 10).boxed().collect(Collectors.toList());
        System.out.println("--------------------");
        System.out.println(test);
        Permutation.AnmStdout((ArrayList<Integer>) test, 2);
        System.out.println("--------------------");
        System.out.println(test);
        Permutation.CnmStdout((ArrayList<Integer>) test, 2);
    }
}
