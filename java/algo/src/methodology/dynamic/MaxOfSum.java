package methodology.dynamic;

import competition.utility.ArrayUtility;
import methodology.maxOfSum.MaxSumOfSubset;
import methodology.maxOfSum.MaxSumOfSubset2;
import methodology.maxOfSum.MaxSumOfSubset3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by zzt on 5/15/16.
 * <p>
 * <h3></h3>
 */
public class MaxOfSum {

    private static class Sum {
        private int sum;
        /**
         * exclusive
         */
        private int endIndex;
        private ArrayList<Integer> possibleStart = new ArrayList<>();

        Sum(int sum, int endIndex) {
            this.sum = sum;
            this.endIndex = endIndex;
        }

        boolean reachEnd(int nowEnd) {
            return endIndex == nowEnd;
        }

        public Sum setSum(int sum) {
            this.sum = sum;
            return this;
        }

        public boolean isEmpty() {
            assert sum >= 0;
            return sum == 0;
        }

        void updateEnd(int end) {
            endIndex = end;
        }

        void addSumAndUpdateEnd(int last, int end) {
            sum += last;
            updateEnd(end);
        }


        void tryPossibleStart(int[] nums, int end) {
            int max = 0;
            int endI = end;
            int previousSum = 0;
            for (int i = possibleStart.size() - 1; i >= 0; i--) {
                final Integer start = possibleStart.get(i);
                final int sum = Arrays.stream(nums, start, endI).sum() + previousSum;
                if (max < sum) {
                    max = sum;
                }
                endI = start;
                previousSum = sum;
            }
            if (max > this.sum) {
                sum = max;
                endIndex = end;
            }
        }

        Sum tryAddStart(int[] nums, int i) {
            assert nums[i] > 0;
            if (((i > 0) && (nums[i - 1] <= 0)) || (i == 0)) {
                possibleStart.add(i);
            }
            return this;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return "Sum{" +
                    "sum=" + sum +
                    ", endIndex=" + endIndex +
                    ", possibleStart=" + possibleStart +
                    '}';
        }
    }

    private static Sum compute(int[] nums, int end, Sum lastOutCome) {
        // base case
        final int last = nums[end - 1];
        if (end == 1) {
            Sum outCome = new Sum(last, 1);
            return last > 0 ?
                    outCome.tryAddStart(nums, 0) : outCome.setSum(0);
        }

        if (last > 0) {
            lastOutCome.tryAddStart(nums, end - 1);
            if (lastOutCome.reachEnd(end - 1)) {
                lastOutCome.addSumAndUpdateEnd(last, end);
            } else { // subset in the middle
                lastOutCome.tryPossibleStart(nums, end);
            }
        } else {
            if (lastOutCome.isEmpty()) {
                lastOutCome.updateEnd(end);
            }
        }
        return lastOutCome;
    }

    private static Sum compute(int[] integers) {
        Sum lastOutCome = null;
        for (int i = 1; i <= integers.length; i++) {
            lastOutCome = compute(integers, i, lastOutCome);
        }
        return lastOutCome;
    }

    public static int myMaxSum(int[] ints) {
        return compute(ints).sum;
    }

    public static void main(String[] args) {
//        int[] t = {-8, 0, 16, 15, 48, -24, 14, -34, 33, -1, 30, -11, 4, 30, 27, -30, 3, 33, -6, -7, };
//        showTest(t);
        randomTest();
        //        testAll();
    }

    private static void randomTest() {
        for (int j = 0; j < 100; j++) {
            int[] integers = ArrayUtility.randomInts(123 + j, 20, -50, 50);
            showTest(integers);
        }
    }

    private static void showTest(int[] integers) {
        final Sum sum = compute(integers);
        final int compute = MaxOfSumOpt.maxSumDynamic(integers);
        System.out.println("Input: ");
        IntStream.of(integers).forEach(i -> System.out.print(i + ", "));
        System.out.println("\nOutput: ");
        System.out.println(sum);
        assert sum.getSum() == compute;
    }

    private static void testAll() {
        Random random = new Random();
        for (int j = 0; j < 100; j++) {

            int[] integers = ArrayUtility.randomInts(23 + j, 20, -50, 50);
            Sum out = compute(integers);

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
                IntStream.of(integers).forEach(i -> System.out.print(i + ", "));
                System.out.println("\nOutput: ");
                System.out.println(p1 + " " + p2 + " " + p3);
                System.out.println(out);
            }
        }
    }

}
