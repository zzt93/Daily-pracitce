package competition.practice.round1C2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by zzt on 5/8/16.
 * <p>
 * Usage:
 */
public class Senators {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/senator-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        String res;
        for (int i = 0; i < trail; i++) {
            in.nextLine();
            res = eva(in.oneLineToInt(" "));
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    static class Party implements Comparable<Party> {
        final int num;
        final char c;

        public Party(int num, char c) {
            this.num = num;
            this.c = c;
        }

        @Override
        public int compareTo(Party o) {
            return -Integer.compare(num, o.num);
        }


    }

    private static String eva(ArrayList<Integer> list) {
        char c = 'A';
        int sum = 0;
        PriorityQueue<Party> parties = new PriorityQueue<>(list.size());
        for (Integer integer : list) {
            parties.add(new Party(integer, c++));
            sum += integer;
        }
        StringBuilder res = new StringBuilder();

        while (!parties.isEmpty()) {
            final Party max = parties.poll();
            Party sec;
            if ((sec = parties.poll()) != null) {
                res.append(sec.c);
                if (sec.num > 1) {
                    parties.add(new Party(sec.num - 1, sec.c));
                }
            }
            res.append(max.c).append(" ");
            if (max.num > 1) {
                parties.add(new Party(max.num - 1, max.c));
            }
        }
        if (sum % 2 != 0) {// swap last two elements?
            final String substring = res.substring(res.length() - 5);
            final StringBuilder tmp = res.delete(res.length() - 5, res.length());
            tmp.append(substring.substring(3)).append(substring.substring(0, 3));
            res = tmp;
        }


        return res.toString();
    }
}
