package competition.practice.kickD2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 7/16/17.
 * <p>
 * <h3></h3>
 */
public class Sightseeing {

    private static class Bus {
        int s;
        int f;
        int d;

        public Bus(int s, int f, int d) {
            this.s = s;
            this.f = f;
            this.d = d;
        }

        public int waiting(int now) {
            int i = now - s;
            if (i < 0) {
                return s - now;
            }
            int div = i / f;
            if (div * f == i) {
                return 0;
            }
            return (div + 1) * f - i;
        }
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/sightseeing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            int n = in.nextInt();
            int sight = in.nextInt();
            int finish = in.nextInt();
            ArrayList<Bus> buses = new ArrayList<>(n);
            for (int i1 = 0; i1 < n - 1; i1++) {
                buses.add(new Bus(in.nextInt(), in.nextInt(), in.nextInt()));
            }
            res = maxCities(n, sight, finish, buses);
            out.println("Case #" + (i + 1) + ": " + (res == -1 ? "IMPOSSIBLE" : res));
        }
    }

    private static int maxCities(int n, int sight, int finish, ArrayList<Bus> buses) {
        int now = 0;
        Comparator<int[]> objectComparator = Comparator.comparingInt(a -> -a[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(objectComparator.thenComparingInt(a -> -a[1]));
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);
            int time = sight + bus.waiting(now + sight) + bus.d;
            queue.add(new int[]{bus.waiting(now + sight), bus.waiting(now), i, now, bus.d, sight, bus.f});
            now += time;
            while (now > finish) {
                ArrayList<int[]> polls = new ArrayList<>();
                int[] poll = queue.poll();
                while (!queue.isEmpty() && (isaBoolean(poll) || (i < poll[2]))) {
                    if (i >= poll[2]) {
                        polls.add(poll);
                    }
                    poll = queue.poll();
                }
                if (queue.isEmpty() && ((isaBoolean(poll)) || (i < poll[2]))) {
                    return -1;
                }
                queue.addAll(polls);
                now = poll[3] + poll[1] + poll[4];
                i = poll[2];
            }
        }
        return queue.size();
    }

    private static boolean isaBoolean(int[] poll) {
        return poll[0] == poll[1] && poll[5] != poll[6];
    }

}
