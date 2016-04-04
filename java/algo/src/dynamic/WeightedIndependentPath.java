package dynamic;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Problem description:
 * Given a path graph with node have some weight, i.e. like O--O--O--O,
 * find the largest sum of weight for a independent set of node, i.e. every two
 * node of this are not adjacent.
 * <p>
 * Input:
 * a line of weight of node in integer
 * e.g. 1 4 5 4
 * <p>
 * Output:
 * largest sum
 * e.g. 8
 */
public class WeightedIndependentPath {

    public int compute(List<Integer> integers) {
        int size = integers.size();
        if (size == 1) {
            return integers.get(0);
        } else if (size == 2) {
            return Math.max(integers.get(0), integers.get(1));
        }
        int fn_1 = compute(integers.subList(0, size - 1));
        int fn_2 = compute(integers.subList(0, size - 2));
        return Math.max(fn_1, fn_2 + integers.get(size - 1));
    }

    public static void main(String[] args) {
        //        MyIn in = new MyIn();
        //        String input = in.nextLine();
        Random random = new Random();
        List<Integer> integers = random.ints(5, 1, 20).boxed().collect(Collectors.toList());
        System.out.println("Input: ");
        integers.stream().forEach(i -> {
            System.out.print(i + ", ");
        });
        System.out.println("\nOutput: ");
        System.out.println(new WeightedIndependentPath().compute(integers));
    }
}
