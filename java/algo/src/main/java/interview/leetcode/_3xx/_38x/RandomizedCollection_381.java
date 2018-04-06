package interview.leetcode._3xx._38x;

import java.util.*;

/**
 * Created by zzt on 4/5/18.
 * <p>
 * <h3>Swap can be used to delete a element efficiently in arraylist</h3>
 */
public class RandomizedCollection_381 {

    private HashMap<Integer, HashSet<Integer>> m = new HashMap<>();
    private ArrayList<Integer> l = new ArrayList<>();
    private Random r = new Random(12);
    public RandomizedCollection_381() {

    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        int s = l.size();
        l.add(val);
        if (m.containsKey(val)) {
            m.get(val).add(s);
            return false;
        } else {
            HashSet<Integer> list = new HashSet<>();
            list.add(s);
            m.put(val, list);
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (m.containsKey(val)) {
            HashSet<Integer> c = m.get(val);
            int i = c.iterator().next();
            c.remove(i);
            if (c.size() == 0) {
                m.remove(val);
            }
            int last = l.size()-1;
            if (i != last) {
                HashSet<Integer> t = m.get(l.get(last));
                t.remove(last);
                t.add(i);
                Collections.swap(l, i, last);
            }
            l.remove(last);
            return true;
        }
        return false;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return l.get(r.nextInt(l.size()));
    }
}
