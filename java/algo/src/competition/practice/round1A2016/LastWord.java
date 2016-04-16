package competition.practice.round1A2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by zzt on 3/28/15.
 * <p/>
 */
public class LastWord {


    public static final int NOT_LETTER = 'A' - 1;

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/word-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        String res;
        for (int i = 0; i < trail; i++) {
            res = lastWord(in.nextLine());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    static class Res {
        char max;
        int i;

        public Res(char max, int j) {
            this.max = max;
            this.i = j;
        }
    }

    private static String lastWord(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        char[] chars = word.toCharArray();
        Res first = max(chars, chars.length - 1);
        HashSet<Integer> used = new HashSet<>();
        Res tmp = first;
        while (tmp.i > 0) {
            sb.append(tmp.max);
            used.add(tmp.i);
            tmp = max(chars, tmp.i - 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used.contains(i)) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    private static Res max(char[] chars, int end) {
        char max = NOT_LETTER;
        int j = -1;
        for (int i = end; i >= 0; i--) {
            if (chars[i] > max) {
                max = chars[i];
                j = i;
            }
        }
        return new Res(max, j);
    }
}
