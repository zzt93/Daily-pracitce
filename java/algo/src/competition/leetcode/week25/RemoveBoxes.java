package competition.leetcode.week25;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class RemoveBoxes {


    public int removeBoxes(int[] boxes) {
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>(boxes.length);
        for (int i = 0; i < boxes.length; i++) {
            int box = boxes[i];
            LinkedList<Integer> tmp;
            if (map.containsKey(box)) {
                tmp = map.get(box);
            } else {
                tmp = new LinkedList<>();
            }
            tmp.add(i);
        }
        int some = removeAllConnected(map);
        if (map.isEmpty()) {
            return some;
        }

        return 0;
    }

    private int removeAllConnected(HashMap<Integer, LinkedList<Integer>> map) {
        int res = 0;
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            LinkedList<Integer> list = map.get(key);
            if (allConnected(list)) {
                iterator.remove();
                res += (list.size() * list.size());
            }
        }
        return res;
    }

    private boolean allConnected(LinkedList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        Integer first = iterator.next();
        while (iterator.hasNext()) {
            if (first + 1 != iterator.next()) {
                return false;
            }
            first++;
        }
        return true;
    }
}
