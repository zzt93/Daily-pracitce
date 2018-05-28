package competition.leetcode.w86;

import java.util.HashSet;
import java.util.List;

/**
 * @author zzt
 */
public class KeysAndRooms {

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    HashSet<Integer> set = new HashSet<>();
    dfs(set, rooms, 0);
    return set.size() == rooms.size();
  }

  private void dfs(HashSet<Integer> set, List<List<Integer>> rooms, int now) {
    if (set.contains(now)) return;
    set.add(now);
    for (Integer next : rooms.get(now)) {
      dfs(set, rooms, next);
    }
  }

}
