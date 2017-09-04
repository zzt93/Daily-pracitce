package pearls;

import competition.utility.Swap;

import java.util.ArrayList;
import java.util.Arrays;
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
            return;
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
        int[] res = new int[m];
        Cnm(list, 0, 0, m, res);
    }

    /**
     * @param list      source
     * @param resI      result index
     * @param srcI      source index
     * @param remaining to finish
     * @param res       result
     *
     * @implNote Thought: every element in the array have two fate: selected or not
     */
    private static void Cnm(ArrayList<Integer> list, int resI, int srcI, int remaining, int[] res) {
        if (remaining == 0) {
            System.out.println(Arrays.toString(res));
            return;
        }
        if (srcI >= list.size()) {
            return;
        }
        // choose now element
        res[resI] = list.get(srcI);
        Cnm(list, resI + 1, srcI + 1, remaining - 1, res);
        // skip now element
        Cnm(list, resI, srcI + 1, remaining, res);
    }

    public static void main(String[] args) {
        List<Integer> test = new Random(47).ints(4, 0, 10).boxed().collect(Collectors.toList());
        System.out.println("--------------------");
        System.out.println(test);
        Permutation.AnmStdout((ArrayList<Integer>) test, 2);
        System.out.println("--------------------");
        System.out.println(test);
        Permutation.CnmStdout((ArrayList<Integer>) test, 2);
        Permutation.CnmStdout((ArrayList<Integer>) test, 0);
    }
}
