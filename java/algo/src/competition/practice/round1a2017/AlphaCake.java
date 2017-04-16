package competition.practice.round1a2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by zzt on 4/15/17.
 * <p>
 * <h3></h3>
 */
public class AlphaCake {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/alpha-cake-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int r = in.nextInt();
            in.nextLine();
            ArrayList<char[]> cake = new ArrayList<>(r);
            for (int i1 = 0; i1 < r; i1++) {
                char[] e = in.nextLine().toCharArray();
                cake.add(e);
            }
            cakeSplit(cake);
            out.println("Case #" + (i + 1) + ": ");
            for (char[] chars : cake) {
                out.println(new String(chars));
            }
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static void cakeSplit(ArrayList<char[]> cake) {
        HashSet<Integer> blank = new HashSet<>();
        for (int i = 0; i < cake.size(); i++) {
            char[] chars = cake.get(i);
            int first = hasChars(chars, 0);
            if (first == -1) {
                blank.add(i);
            } else {
                for (int x = 0; x < first; x++) {
                    chars[x] = chars[first];
                }
                int second = hasChars(chars, first + 1);
                while (second != -1) {
                    for (int y = first + 1; y < second; y++) {
                        chars[y] = chars[second];
                    }
                    first = second;
                    second = hasChars(chars, second + 1);
                }
                for (int z = first + 1; z < chars.length; z++) {
                    chars[z] = chars[first];
                }
            }
        }
        int lastHas = -1;
        int c = 0;
        for (int i = 0; i < cake.size(); i++) {
            if (blank.contains(i)) {
                if (lastHas == -1) {
                    c++;
                } else {
                    cake.set(i, cake.get(lastHas));
                }
            } else {
                lastHas = i;
            }
        }
        for (int i = 0; i < c; i++) {
            cake.set(i, cake.get(c));
        }
    }

    private static int hasChars(char[] chars, int s) {
        for (int i = s; i < chars.length; i++) {
            char c = chars[i];
            if (c != '?') {
                return i;
            }
        }
        return -1;
    }

}
