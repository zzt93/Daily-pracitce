package competition.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by zzt on 3/21/15.
 * This is a simple implementation of Knuth shuffle
 * for learning
 */
public class Shuffle {
    static Random r = new Random();
    public static <T> void shuffle(ArrayList<T> ts) {
        for (int i = 1; i < ts.size(); i++) {
            int aim = r.nextInt(i+1);
            Swap.swap(ts, i, aim);
        }
    }

    private static <T> void wrongShuffle(ArrayList<T> ts) {
        for (int i = 1; i < ts.size(); i++) {
            int aim = r.nextInt(ts.size());
            Swap.swap(ts, i, aim);
        }
    }

    private static void reset(ArrayList<Integer> cards) {
        if (cards == null) {
            return;
        }
        cards.clear();
        cards.add(1);
        cards.add(2);
        cards.add(3);
    }

    private static <K> void checkPutAdd(HashMap<K, Integer> map, K aim) {
        if (map == null) {
            return;
        }
        if (map.containsKey(aim)) {
            int i = map.get(aim);
            map.put(aim, i+1);
        } else {
            map.put(aim, 0);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> cards = new ArrayList();
        reset(cards);

        int times = 1200000;
        HashMap<String, Integer> count = new HashMap<String, Integer>(3);
        for (int i = 0; i < times; i++) {
            shuffle(cards);

            switch (cards.get(0)) {
                case 1:
                    if (cards.get(1) == 2) {
                        checkPutAdd(count, "1 2 3:");
                    } else {
                        checkPutAdd(count, "1 3 2:");
                    }
                    break;
                case 2:
                    if (cards.get(1) == 1) {
                        checkPutAdd(count, "2 1 3:");
                    } else {
                        checkPutAdd(count, "2 3 1:");
                    }
                    break;
                case 3:
                    if (cards.get(1) == 1) {
                        checkPutAdd(count, "3 1 2:");
                    } else {
                        checkPutAdd(count, "3 2 1:");
                    }
                    break;
                default:
                    assert false;
            }

            reset(cards);
        }

        for (String s : count.keySet()) {
            System.out.println(s + count.get(s));
        }
    }
}
