package dynamic;

import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Problem description:
 * <p>
 * finding the longest sequence common to all sequences in a set of sequences.
 * It differs from problems of finding common subStrings:
 * unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences.
 */
public class LCSequence {

    private class Outcome {
        TreeSet<StringBuilder> builders = new TreeSet<>((sb1, sb2) -> {
            return sb1.toString().compareTo(sb2.toString());
        });
        int count;

        public Outcome(int count) {
            this.count = count;
        }

        Outcome merge(Outcome o) {
            builders.addAll(o.builders);
            return this;
        }

        void addChar(char c) {
            if (builders.isEmpty()) {
                builders.add(new StringBuilder());
            }
            for (StringBuilder builder : builders) {
                builder.append(c);
            }
            count++;
        }

        @Override
        public String toString() {
            return "Outcome{" +
                    "builders=" + builders.stream().map(StringBuilder::toString).collect(Collectors.joining("; ")) +
                    ", count=" + count +
                    '}';
        }

        public Outcome copy() {
            Outcome outcome = new Outcome(count);
            for (StringBuilder builder : builders) {
                outcome.builders.add(new StringBuilder(builder));
            }
            return outcome;
        }
    }

    private class EndIndex {
        int l1;
        int l2;

        public EndIndex(int l1, int l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EndIndex endIndex = (EndIndex) o;

            return l1 == endIndex.l1 && l2 == endIndex.l2;

        }

        @Override
        public int hashCode() {
            int result = l1;
            result = 31 * result + l2;
            return result;
        }
    }

    public Outcome LCS(String s1, String s2) {
        return lcs2(s1, s1.length(), s2, s2.length(), new WeakHashMap<EndIndex, Outcome>());
    }

    /**
     * This implementation is wrong.
     * For example, "adfcab", "adcaf" prove this way is wrong: should be "adca"
     * @param s1 first string
     * @param l1 first length
     * @param s2 second string
     * @param l2 second length
     * @param endIndexOutcomeWeakHashMap map for memorization
     * @return all lcs and length count
     */
    private Outcome lcs1(String s1, int l1, String s2, int l2,
                         WeakHashMap<EndIndex, Outcome> endIndexOutcomeWeakHashMap) {
        if (l1 == 0 || l2 == 0) {
            return new Outcome(0);
        }
        // check memorization
        EndIndex key = new EndIndex(l1, l2);
        //        if (endIndexOutcomeWeakHashMap.containsKey(key)) {
        //            return endIndexOutcomeWeakHashMap.get(key).copy();
        //        }
        char c1 = s1.charAt(l1 - 1);
        char c2 = s2.charAt(l2 - 1);
        if (c1 == c2) {
            Outcome lcs = lcs1(s1, l1 - 1, s2, l2 - 1, endIndexOutcomeWeakHashMap);
            lcs.addChar(c1);
            endIndexOutcomeWeakHashMap.put(key, lcs);
            return lcs;
        } else {
            int newL1 = s1.lastIndexOf(c2, l1 - 1);
            int newL2 = s2.lastIndexOf(c1, l2 - 1);
            if (newL1 == newL2 && newL1 == -1) {
                Outcome lcs = lcs1(s1, l1 - 1, s2, l2 - 1, endIndexOutcomeWeakHashMap);
                endIndexOutcomeWeakHashMap.put(key, lcs);
                return lcs;
            }
            // if not found, newL1 == -1, will send 0 to next loop
            /**
             * "adfcab", "adcaf" prove this way is wrong: should be "adca"
             */
            Outcome o1 = lcs1(s1, newL1 + 1, s2, l2, endIndexOutcomeWeakHashMap);
            Outcome o2 = lcs1(s1, l1, s2, newL2 + 1, endIndexOutcomeWeakHashMap);
            if (o1.count == o2.count) {
                Outcome merge = o1.merge(o2);
                endIndexOutcomeWeakHashMap.put(key, merge);
                return merge;
            }
            Outcome outcome = o1.count > o2.count ? o1 : o2;
            endIndexOutcomeWeakHashMap.put(key, outcome);
            return outcome;
        }
    }

    private Outcome lcs2(String s1, int l1, String s2, int l2,
                         WeakHashMap<EndIndex, Outcome> endIndexOutcomeWeakHashMap) {
        if (l1 == 0 || l2 == 0) {
            return new Outcome(0);
        }
        // check memorization
        EndIndex key = new EndIndex(l1, l2);
        if (endIndexOutcomeWeakHashMap.containsKey(key)) {
            return endIndexOutcomeWeakHashMap.get(key).copy();
        }
        char c1 = s1.charAt(l1 - 1);
        char c2 = s2.charAt(l2 - 1);
        if (c1 == c2) {
            Outcome lcs = lcs2(s1, l1 - 1, s2, l2 - 1, endIndexOutcomeWeakHashMap);
            lcs.addChar(c1);
            endIndexOutcomeWeakHashMap.put(key, lcs);
            return lcs.copy();
        } else {
            Outcome o1 = lcs2(s1, l1 - 1, s2, l2, endIndexOutcomeWeakHashMap);
            Outcome o2 = lcs2(s1, l1, s2, l2 - 1, endIndexOutcomeWeakHashMap);
            if (o1.count == o2.count) {
                Outcome merge = o1.merge(o2);
                endIndexOutcomeWeakHashMap.put(key, merge);
                return merge.copy();
            }
            Outcome outcome = o1.count > o2.count ? o1 : o2;
            endIndexOutcomeWeakHashMap.put(key, outcome);
            return outcome.copy();
        }
    }

    public static void main(String[] args) {
        System.out.println(new LCSequence().LCS("adfcba", "adcafb"));
    }
}
