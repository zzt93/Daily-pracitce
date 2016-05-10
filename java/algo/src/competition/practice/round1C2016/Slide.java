package competition.practice.round1C2016;

import competition.utility.MyIn;
import competition.utility.MyOut;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zzt on 5/8/16.
 * <p>
 * <h3>Conclusion:</h3>
 * <li>dynamic thinking in graph</li>
 * <li>recursive thinking in graph</li>
 * <p>You know from 1 to B is at most 2^B-2 paths, so 2 to B is 2^B-3, 3 to B is 2^B-4...
 * Then choose to connect 2 and/or 3 and/or ...
 * </p>
 * <p>very much like the thought of minimal spanning tree/ shortest path: divide a large
 * problem into smaller one. What a little difference is here we merge the latter part
 * rather than starting part.
 * </p>
 */
@Deprecated
public class Slide {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/slide-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        ArrayList<int[]> res;
        for (int i = 0; i < trail; i++) {
            res = slide(in.nextInt(), in.nextInt());
            out.println("Case #" + (i + 1) + ": " + (res.isEmpty() ? "IMPOSSIBLE" : "POSSIBLE"));
            if (!res.isEmpty()) {
                for (int[] re : res) {
                    for (int i1 : re) {
                        out.print(i1);
                    }
                    out.println();
                }
            }
        }
    }

    public static ArrayList<int[]> Cnm(ArrayList<Integer> list, int m) {
        int[] tmp = new int[m];
        ArrayList<int[]> res = new ArrayList<>();
        Cnm(list, 0, 0, m, tmp, res);
        return res;
    }

    private static void Cnm(final ArrayList<Integer> list, int resI, int srcI, int remaining, int[] res, ArrayList<int[]> ints) {
        if (remaining == 0) {
            ints.add(Arrays.copyOf(res, res.length));
            return;
        }
        if (srcI >= list.size()) {
            return;
        }
        // choose now element
        res[resI] = list.get(srcI);
        Cnm(list, resI + 1, srcI + 1, remaining - 1, res, ints);
        // skip now element
        Cnm(list, resI, srcI + 1, remaining, res, ints);
    }

    private static ArrayList<int[]> slide(int b, int m) {
        ArrayList<int[]> res = new ArrayList<>();
        if (m > Math.pow(2, b - 2)) {
            return res;
        }
        for (int i = 0; i < b; i++) {
            res.add(new int[b]);
        }
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 1; i < b - 1; i++) {
            input.add(i);
        }
        int count = 0;
        while (m > 0) {
            ArrayList<int[]> cnms = Cnm(input, count);
            for (int[] cnm : cnms) {
                LinkedList<Integer> path = initPath(b);
                for (int i : cnm) {
                    path.add(i);
                }
                path.add(b - 1);
                initRes(path, res);
                m--;
                if (m == 0) {
                    break;
                }
            }
            count++;
        }
        return res;
    }

    private static void initRes(LinkedList<Integer> path, ArrayList<int[]> res) {
        for (int i = 1; i < path.size(); i++) {
            int first = path.get(i - 1);
            int sec = path.get(i);
            res.get(first)[sec] = 1;
        }
    }

    @NotNull
    private static LinkedList<Integer> initPath(int b) {
        final int start = 0;
        LinkedList<Integer> path = new LinkedList<>();
        path.add(start);
        return path;
    }
}
