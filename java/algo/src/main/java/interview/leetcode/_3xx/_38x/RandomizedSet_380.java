package interview.leetcode._3xx._38x;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by zzt on 4/5/18.
 * <p>
 * <h3></h3>
 */
public class RandomizedSet_380 {

    private HashMap<Integer, Integer> i2v = new HashMap<>();
    private HashMap<Integer, Integer> v2i = new HashMap<>();
    private LinkedList<Integer> q = new LinkedList<>();
    private Random r = new Random(12);
    private int c = 0;
    /** Initialize your data structure here. */
    public RandomizedSet_380() {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        int s = i2v.size();
        if (!q.isEmpty()) s = q.poll();
        boolean res;
        if (res = (v2i.putIfAbsent(val, s) == null)) {
            i2v.put(s, val);
            c++;
        }
        return res;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer res;
        if ((res = v2i.remove(val)) != null) {
            i2v.remove(res);
            q.add(res);
        }
        return res != null;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Integer res, i;
        do {
            i = r.nextInt(c);
        } while ((res = i2v.get(i)) == null);
        return res;
    }

    public static void main(String[] args) {
        RandomizedSet_380 r = new RandomizedSet_380();
        System.out.println(r.insert(1));
        System.out.println(r.remove(2));
        System.out.println(r.insert(2));
        System.out.println(r.getRandom());
        System.out.println(r.remove(1));
        System.out.println(r.insert(2));
        System.out.println(r.getRandom());
        System.out.println(r.remove(2));
        System.out.println(r.getRandom());
    }
}
