package methodology.dynamic;

import java.util.List;

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
 * <p>
 * Input:
 * -35, -44, -29, 38, -44, -28, -49, -16, 30, 33, 24, 11, 29, -16, -6, 24, -39, -7, 17, 45,
 * Output:
 * Outcome{endIndex=20, sum=45, reachEnd=true}
 */
@Deprecated
public class WrongMaxOfSum {

    private static class Outcome {
        /**
         * exclusive
         */
        private int endIndex;
        int sum;
        /**
         * if true, mean {@link #endIndex} == end of current list
         */
        boolean reachEnd;

        Outcome(int sum, boolean reachEnd, int endIndex) {
            this.sum = sum;
            this.reachEnd = reachEnd;
            this.endIndex = endIndex;
        }

        public int getSum() {
            return sum;
        }

        void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        boolean isReachEnd() {
            return reachEnd;
        }

        public Outcome setSum(int sum) {
            this.sum = sum;
            return this;
        }

        void setReachEnd(boolean reachEnd) {
            this.reachEnd = reachEnd;
        }

        Outcome addSum(Integer last) {
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

    /**
     * @param integers input integer
     * @param end      end index, exclusive
     *
     * @return max of sum of consecutive subset
     */
    public static Outcome compute(List<Integer> integers, int end) {
        Integer last = integers.get(end - 1);
        if (end == 1) {
            /**
             * always be reachEnd:
             * if last > 0
             *      outcome range [0, 1)
             * else
             *      outcome range [1, 1)
             */
            Outcome outcome = new Outcome(last, true, 1);
            return last > 0 ? outcome : outcome.setSum(0);
        }
        Outcome outcome = compute(integers, end - 1);
        if (last <= 0) {
            if (outcome.getSum() > 0) {
                outcome.setReachEnd(false);
            } else {
                assert outcome.getSum() == 0;
                outcome.setReachEnd(true);
                outcome.setEndIndex(end);
            }
        } else {// last > 0
            if (outcome.isReachEnd()) {
                assert outcome.endIndex == end - 1;
                // update subset end bound
                outcome.setEndIndex(end);
                outcome.addSum(last);
            } else {// max subset is in the middle
                // first choose a larger part
                int fn_1 = outcome.getSum();
                if (fn_1 < last) { // previous sum is smaller than now element
                    outcome.setReachEnd(true);
                    outcome.setEndIndex(end);
                    outcome.setSum(last);
                } else {
                    int sum = integers.subList(outcome.endIndex, end)
                            .stream().mapToInt(Integer::intValue).sum();
                    if (sum > 0) {// this value deserve to add
                        outcome.setEndIndex(end);
                        outcome.setReachEnd(true);
                        outcome.addSum(sum);
                    }
                }
            }
        }
        return outcome;
    }

}
