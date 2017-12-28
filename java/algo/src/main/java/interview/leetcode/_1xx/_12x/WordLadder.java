package interview.leetcode._1xx._12x;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by zzt on 9/28/17.
 * <p>
 * <h3></h3>
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        StringBuilder start = new StringBuilder(beginWord);
        Queue<StringBuilder> queue = new LinkedList<>();
        queue.add(start);
        queue.add(null);
        int level = 1;
        while (!queue.isEmpty()) {
            StringBuilder sb = queue.poll();
            if (sb == null) {
                if (queue.isEmpty()) {
                    break;
                } else {
                    level++;
                    queue.add(null);
                    continue;
                }
            }
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                for (int x = 0; x < 26; x++) {
                    sb.setCharAt(i, (char) (x + 'a'));
                    String o = sb.toString();
                    if (set.contains(o)) {
                        set.remove(o);
                        if (o.equals(endWord)) {
                            return level + 1;
                        }
                        queue.add(new StringBuilder(o));
                    }
                }
                sb.setCharAt(i, c);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot",
                "dog", "lot", "log", "cog")));
        System.out.println(wordLadder.ladderLength("hit", "cog", Lists.newArrayList("hot", "dot",
                "dog", "lot", "log")));
        System.out.println(wordLadder.ladderLength("hit", "hot", Lists.newArrayList("hot", "dot",
                "dog", "lot", "log", "cog")));
    }
}
