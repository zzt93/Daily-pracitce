package interview.leetcode._2xx._21x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 12/11/17.
 * <p>
 * <h3>Matrix traversal: how to prune</h3>
 * <ul>
 * <li>backtrack: test all words</li>
 * <li>find start and end, find path between them. Opt: choose less branch</li>
 * </ul>
 * <h3>Tips</h3>
 * <ul>
 *     <li>Remove visited[m][n] completely by modifying board[i][j] = '#' directly.</li>
 * </ul>
 */
public class WordSearch2 {

    public List<String> findWords(char[][] board, String[] words) {
        LinkedList<int[]>[] map = new LinkedList[26];
        for (int i = 0; i < map.length; i++) {
            map[i] = new LinkedList<>();
        }
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                map[row[j] - 'a'].add(new int[]{i, j});
            }
        }
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (set.contains(word)) {
                continue;
            } else {
                set.add(word);
            }
            char[] cs = word.toCharArray();
            int l = word.length();
            char sc = cs[0];
            char ec = cs[l - 1];
            LinkedList<int[]> is = map[sc - 'a'];
            LinkedList<int[]> es = map[ec - 'a'];
            if (is.size() < es.size()) {
                for (int[] i : is) {
                    if (dfs(board, i, es, true, cs, 1, l)) {
                        res.add(word);
                        break;
                    }
                }
            } else {
                for (int[] e : es) {
                    if (dfs(board, e, is, false, cs, 0, l-1)) {
                        res.add(word);
                        break;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(char[][] board, int[] xy, List<int[]> pre, boolean b, char[] cs, int s,
                        int e) {
        if (s >= e) return isConnected(xy, pre);

        char aim = cs[b ? s : e-1];
        char aimR = cs[!b ? s : e-1];
        char c = board[xy[0]][xy[1]];
        board[xy[0]][xy[1]] = '#';
        List<int[]> next = new ArrayList<>(4);
        if (xy[0] != 0 && board[xy[0] - 1][xy[1]] == aim) next.add(new int[]{xy[0] - 1, xy[1]});
        if (xy[0] != board.length - 1 && board[xy[0] + 1][xy[1]] == aim)
            next.add(new int[]{xy[0] + 1, xy[1]});
        if (xy[1] != 0 && board[xy[0]][xy[1] - 1] == aim) next.add(new int[]{xy[0], xy[1] - 1});
        if (xy[1] != board[0].length - 1 && board[xy[0]][xy[1] + 1] == aim)
            next.add(new int[]{xy[0], xy[1] + 1});

        if (next.size() < pre.size()) {
            int i = b ? s++ : e--;
            for (int[] ints : next) {
                if (dfs(board, ints, pre, b, cs, s, e)) {
                    board[xy[0]][xy[1]] = c;
                    return true;
                }
            }
        } else {
            b = !b;
            int i = b ? s++ : e--;
            for (int[] ints : pre) {
                if (board[ints[0]][ints[1]] == aimR && dfs(board, ints, next, b, cs, s, e)) {
                    board[xy[0]][xy[1]] = c;
                    return true;
                }
            }
        }
        board[xy[0]][xy[1]] = c;
        return false;
    }

    private boolean isConnected(int[] xy, List<int[]> pre) {
        for (int[] ints : pre) {
            if (ints[0] == xy[0] && ints[1] == xy[1]) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        WordSearch2 s = new WordSearch2();
        System.out.println(s.findWords(new char[][]{new char[]{'b','a', 'b','b','a'}}, new String[]{"aba","abba"}));
        System.out.println(s.findWords(new char[][]{new char[]{'o', 'a', 'a', 'n'}, new
                char[]{'e', 't', 'a', 'e'}, new char[]{'i', 'h', 'k', 'r'}, new char[]{'i', 'f',
                'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"}));
        System.out.println(s.findWords(new char[][]{new char[]{'a', 'b'}}, new String[]{"aba"}));
        System.out.println(s.findWords(new char[][]{new char[]{'a', 'a'}}, new String[]{"aa"}));
    }
}
