package interview.google.course;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class Synonyms {

    public boolean same(String[] s1, String[] s2, List<List<String>> dict) {
        HashMap<String, Integer> map = new HashMap<>();
        int counter = 0;
        for (List<String> ss: dict) {
            for (String s: ss) {
                map.put(s, counter);
            }
            counter++;
        }
        for (int i = 0; i < s1.length; i++) {
            if (!map.get(s1[i]).equals(map.get(s2[i]))) return false;
        }
        return true;
    }

}
