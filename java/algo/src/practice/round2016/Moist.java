package practice.round2016;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by zzt on 9/30/15.
 * <p>
 * Description:
 * <a href="https://code.google.com/codejam/contest/dashboard?c=6234486#s=p2">Captain</a>
 */
public class Moist {
    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/moist-small-practice-2.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        int res;
        for (int i = 0; i < trail; i++) {
            int cardsNum = in.nextInt();
            in.nextLine();
            ArrayList<String> cards = new ArrayList<>(cardsNum);
            for (int i1 = 0; i1 < cardsNum; i1++) {
                cards.add(in.nextLine());
            }
            res = CountReverse(cards);
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static int CountReverse(ArrayList<String> cards) {
        int count = 0;
        String last = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            final String now = cards.get(i);
            if (last.compareTo(now) > 0) {
                count++;
            } else {
                last = now;
            }
        }
        return count;
    }


}
