package competition.leetcode.week23;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zzt on 3/12/17.
 * <p>
 * <h3></h3>
 */
public class WordAbbr {

    private static class Word {
        private String original;
        private String res;
        private int index;
        private int lp;
        private char[] array;

        Word(int i, String s) {
            index = i;
            original = s;
            array = original.toCharArray();
        }

        void genRes(int lp) {
            if (lp > this.lp) {
                this.lp = lp;
            } else {
                return;
            }
            if (shorter(lp)) {
                final String len = Integer.toString(original.length() - lp - 1);
                res = original.substring(0, lp) + len + original.charAt(original.length() - 1);
            } else {
                res = original;
            }
        }

        boolean shorter(int lp) {
            final int length = original.length();
            int save = length - lp - 1;
            return save > Integer.toString(save).length();
        }

        public int len() {
            return original.length();
        }

        boolean sameAbbr(Word s) {
            final int l1 = len();
            final int l2 = s.len();
            return l1 == l2
                    && charAt(0) == s.charAt(0)
                    && charAt(l1 - 1) == s.charAt(l2 - 1);
        }

        char charAt(int i) {
            return array[i];
        }

        char startChar() {
            return charAt(0);
        }

        public String getRes() {
            if (res == null) {
                genRes(1);
            }
            return res;
        }
    }

    public List<String> wordsAbbreviation(List<String> dict) {
        ArrayList<Word> words = new ArrayList<>(dict.size());
        for (int i = 0; i < dict.size(); i++) {
            words.add(new Word(i, dict.get(i)));
        }

        words.sort(
                Comparator.comparingInt(Word::startChar)
                        .thenComparing(word -> word.charAt(word.len() - 1))
                        .thenComparing(Word::len)
                        .thenComparing(word -> word.original)
        );
        for (int i = 0; i < words.size() - 1; i++) {
            final Word f = words.get(i);
            final Word s = words.get(i + 1);
            int lp;
            if (!f.sameAbbr(s)) {
                lp = 1;
            } else {
                lp = longestPrefix(f, s);
            }
            f.genRes(lp);
            s.genRes(lp);
        }
        words.sort(Comparator.comparing(s -> s.index));

        List<String> res = new ArrayList<>(words.size());
        for (Word word : words) {
            res.add(word.getRes());
        }
        return res;
    }

    private int longestPrefix(Word f, Word s) {
        int p = 1;
        int len = Math.min(f.len(), s.len());
        for (int i = 0; i < len; i++) {
            if (f.charAt(i) == s.charAt(i)) {
                p++;
            } else {
                break;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        final WordAbbr wordAbbr = new WordAbbr();
        System.out.println(wordAbbr.wordsAbbreviation(Lists.newArrayList("like", "god",
                "internal", "me", "internet", "interval", "intension", "face", "intrusion")));
    }
}
