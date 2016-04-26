package methodology.dynamic;

import methodology.maxOfSum.MaxSumOfSubset;
import methodology.maxOfSum.MaxSumOfSubset2;
import methodology.maxOfSum.MaxSumOfSubset3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Problem description:
 * find the maximum of sum of a successive subset in a set;
 * <p>
 * Input:
 * e.g.{21, -9, -4, 28, -29, 45, 83}
 * <p>
 * Output:
 */
public class MaxOfSum {

    private class Outcome {
        private int endIndex;
        int sum;
        boolean reachEnd;

        public Outcome(int sum, boolean reachEnd, int endIndex) {
            this.sum = sum;
            this.reachEnd = reachEnd;
            this.endIndex = endIndex;
        }

        public int getSum() {
            return sum;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public boolean isReachEnd() {
            return reachEnd;
        }

        public Outcome setSum(int sum) {
            this.sum = sum;
            return this;
        }

        public void setReachEnd(boolean reachEnd) {
            this.reachEnd = reachEnd;
        }

        public Outcome addSum(Integer last) {
            sum += last;
            return this;
        }

        @Override
        public String toString() {
            return "Outcome{" +
                    "endIndex=" + endIndex +
                    ", sum=" + sum +
                    ", reachEnd=" + reachEnd +
                    '}';
        }
    }

    public Outcome compute(List<Integer> integers, int start, int end) {
        Integer last = integers.get(end - 1);
        if (end == 1) {
            Outcome outcome = new Outcome(last, true, 1);
            return last > 0 ? outcome : outcome.setSum(0);
        }
        Outcome outcome = compute(integers, 0, end - 1);
        if (last <= 0) {
            if (outcome.getSum() > 0) {
                outcome.setReachEnd(false);
            } else {
                assert outcome.getSum() == 0;
                outcome.setReachEnd(true);
                outcome.setEndIndex(end);
            }
            return outcome;
        } else {// last > 0
            if (outcome.isReachEnd()) {
                // update subset end bound
                outcome.setEndIndex(end);
                return outcome.addSum(last);
            } else {// max subset is in the middle
                int sum = integers.subList(outcome.endIndex, end)
                        .stream().mapToInt(Integer::intValue).sum();
                if (sum > 0) {// this value deserve to add
                    outcome.setEndIndex(end);
                    outcome.setReachEnd(true);
                    return outcome.addSum(sum);
                } else {
                    int fn_1 = outcome.getSum();
                    if (fn_1 < last) {
                        outcome.setReachEnd(true);
                        outcome.setEndIndex(end);
                        return outcome.setSum(last);
                    } else {
                        return outcome;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //        testAll();
        Random random = new Random();
        for (int j = 0; j < 100; j++) {
            List<Integer> integers = random.ints(5, -20, 20).boxed().collect(Collectors.toList());
            Outcome out = new MaxOfSum().compute(integers, 0, integers.size());
            System.out.println("Input: ");
            integers.stream().forEach(i -> {
                System.out.print(i + ", ");
            });
            System.out.println("\nOutput: ");
            System.out.println(out);
        }
    }

    private static void testAll() {
        Random random = new Random();
        for (int j = 0; j < 100; j++) {
            List<Integer> integers = random.ints(5, -20, 20).boxed().collect(Collectors.toList());
            Outcome out = new MaxOfSum().compute(integers, 0, integers.size());

            ArrayList<Double> doubles = new ArrayList<>();
            for (Integer integer : integers) {
                doubles.add(new Double(integer));
            }
            // TODO: 4/4/16 fix the bug of following
            double p2 = new MaxSumOfSubset2(doubles).parts();
            double p1 = new MaxSumOfSubset(doubles).parts();
            double p3 = new MaxSumOfSubset3(doubles).parts();
            if (p1 != p2 || p2 != p3 || p1 != out.getSum()) {
                System.out.println("loop: " + j);
                System.out.println("Input: ");
                integers.stream().forEach(i -> {
                    System.out.print(i + ", ");
                });
                System.out.println("\nOutput: ");
                System.out.println(p1 + " " + p2 + " " + p3);
                System.out.println(out);
            }
        }
    }
}
