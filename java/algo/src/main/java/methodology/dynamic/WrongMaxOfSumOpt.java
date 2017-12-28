package methodology.dynamic;

/**
 * Created by zzt on 5/15/16.
 * <p>
 * <h3></h3>
 * Input:
 * -35, -44, -29, 38, -44, -28, -49, -16, 30, 33, 24, 11, 29, -16, -6, 24, -39, -7, 17, 45,
 * Output:
 * Outcome{endIndex=20, sum=45, reachEnd=true}
 */
@Deprecated
class WrongMaxOfSumOpt {

    static class OutCome {
        /**
         * exclusive
         */
        private int endIndex;
        private int sum;
        /**
         * the sum from [endIndex, now end)
         * so if endIndex == end, assert gap == 0
         */
        private int gap;

        OutCome(int endIndex, int sum, int gap) {
            this.endIndex = endIndex;
            this.sum = sum;
            this.gap = gap;
        }

        boolean reachEnd(int nowEnd) {
            final boolean b = endIndex == nowEnd;
            if (b) {
                assert gap == 0;
            }
            return b;
        }

        OutCome setSumUpdateEnd(int sum, int end) {
            this.sum = sum;
            updateEnd(end);
            return this;
        }

        OutCome addSumAndUpdateEnd(int last, int end) {
            sum += (last + gap);
            updateEnd(end);
            return this;
        }

        boolean canFillGap(int last) {
            return gap + last > 0;
        }

        void addGap(int last) {
            gap = gap + last > 0 ? 0 : gap + last;
            assert gap <= 0;
        }

        public boolean isEmpty() {
            assert sum >= 0;
            return sum == 0;
        }

        void updateEnd(int end) {
            endIndex = end;
            gap = 0;
        }

        public int getSum() {
            return sum;
        }
    }

    static OutCome compute(int[] nums, int end) {
        // base case
        final int last = nums[end - 1];
        if (end == 1) {
            OutCome outCome = new OutCome(1, last, 0);
            return last > 0 ? outCome : outCome.setSumUpdateEnd(0, end);
        }

        final OutCome outCome = compute(nums, end - 1);
        if (last > 0) {
            if (outCome.reachEnd(end - 1)) {
                assert outCome.gap == 0;
                outCome.addSumAndUpdateEnd(last, end);
            } else { // set in the middle
                if (last > outCome.getSum()) { // change to new larger sum
                    outCome.setSumUpdateEnd(last, end);
                } else { // old sum is larger
                    if (outCome.canFillGap(last)) {
                        outCome.addSumAndUpdateEnd(last, end);
                    } else { // can't fill the gap and retain the old sum
                        outCome.addGap(last);
                    }
                }
            }
        } else {
            if (outCome.isEmpty()) {
                outCome.updateEnd(end);
            } else {
                outCome.addGap(last);
            }
        }
        return outCome;
    }
}
