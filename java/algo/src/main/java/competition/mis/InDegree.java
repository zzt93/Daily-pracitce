package competition.mis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InDegree {


  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n = s.nextInt(), m = s.nextInt();
    Set[] g = new Set[n+1];
    Set[] rg = new Set[n+1];
    for (int i = 0; i < n+1; i++) {
      g[i]= new HashSet<Integer>();
      rg[i]= new HashSet<Integer>();
    }
    for (int i = 0; i < m; i++) {
      int u = s.nextInt(), v= s.nextInt();
      if (u != v) {
        g[u].add(v);
        rg[v].add(u);
      }
    }
    Set[] dp = new Set[n+1];
    HashSet<Integer> v = new HashSet<>();
    for (int i = 1; i < n + 1; i++) {
      dfs(v, dp, g, i);
    }
    v.clear();
    Set[] rdp = new Set[n+1];
    for (int i = 1; i < n + 1; i++) {
      dfs(v, rdp, rg, i);
    }
    int res = 0;
    for (int i = 1; i < n + 1; i++) {
      if (rdp[i] != null) {
        if (dp[i] == null || rdp[i].size() > dp[i].size()) {
          res++;
        }
      }
    }
    System.out.println(res);
  }

  private static Set dfs(HashSet<Integer> visiting, Set[] dp, Set[] g, int now) {
    if (visiting.contains(now)) return Collections.emptySet();
    visiting.add(now);

    if (dp[now] != null) {
      return dp[now];
    }
    dp[now] = new HashSet();
    for (Object i : g[now]) {
      dp[now].add(i);
      dp[now].addAll(dfs(visiting, dp, g, (Integer) i));
    }
    visiting.remove(now);
    return dp[now];
  }


}
