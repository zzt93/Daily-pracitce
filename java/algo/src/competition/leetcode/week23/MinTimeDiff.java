package competition.leetcode.week23;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 3/12/17.
 * <p>
 * <h3></h3>
 */
public class MinTimeDiff {

    private static class Time implements Comparable<Time> {
        private int minutes;

        public Time(String timePoint) {
            final String[] split = timePoint.split(":");
            this.minutes = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        @Override
        public int compareTo(Time o) {
            return Integer.compare(minutes, o.minutes);
        }


        public int minus(Time o) {
            return minutes - o.minutes;
        }
    }

    public int findMinDifference(List<String> timePoints) {
        ArrayList<Time> times = new ArrayList<>(timePoints.size());
        for (String timePoint : timePoints) {
            times.add(new Time(timePoint));
        }
        Collections.sort(times);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < times.size() - 1; i++) {
            final Time f = times.get(i);
            final Time s = times.get(i + 1);
            final int minus = s.minus(f);
            assert minus >= 0;
            if (minus < min) {
                min = minus;
            }
        }
        final int acrossDay = times.get(0).minus(times.get(times.size() - 1)) + 24 * 60;
        if (acrossDay < min) {
            min = acrossDay;
        }
        assert min != -1;
        return min;
    }

    public static void main(String[] args) {
        final MinTimeDiff minTimeDiff = new MinTimeDiff();
        System.out.println(minTimeDiff.findMinDifference(Lists.newArrayList("23:59", "00:00")));
        System.out.println(minTimeDiff.findMinDifference(Lists.newArrayList("23:25", "00:00", "12:00", "23:23", "02:30")));
    }

}
