package competition.leetcode.w80;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * @author zzt
 */
public class MostCommonWord {

  public String mostCommonWord(String p, String[] banned) {
    HashMap<String, Integer> c = new HashMap<>();
    HashSet<String> b = new HashSet<>();
    b.addAll(Arrays.asList(banned));
    for (String s: p.split("[\\p{Punct}\\s]+")) {
      String t = s.toLowerCase();
      if (b.contains(t)) continue;
      if (c.containsKey(t)) c.put(t, c.get(t)+1);
      else c.put(t, 1);
    }
    int max = 0; String m = null;
    for (Entry<String, Integer> entry : c.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        m = entry.getKey();
      }
    }
    return m;
  }


}
