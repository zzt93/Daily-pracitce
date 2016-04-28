package pearls;

import competition.utility.MyIn;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zzt on 4/24/16.
 * <h2>Problem: classification</h2>
 * <p>
 *     given a dictionary, find all anagram(equivalent class in which words are
 *     permutations of each other.)
 * </p>
 * <h3>Core idea</h3>
 * make every equivalent class have the same signature so as to reduce the
 * comparison, i.e. I just need to compare the element have same signature
 * (in this case, I use the sum of all chars)
 */
public class Anagrams {

    private final Collection<LinkedList<Word>> values;

    static class Word {
        private TreeMap<Character, LongAdder> counts
                = new TreeMap<>();
        private long signature = 0;
        private String str;
        private long len = 0;

        public Word(String str) {
            str = str.toLowerCase();
            for (char c : str.toCharArray()) {
                counts.computeIfAbsent(c, k -> new LongAdder()).increment();
                signature += c;
            }
            len = str.length();
            this.str = str;
        }

        public boolean permutation(Word word) {
            return len == word.len && wordCompare(word);
        }

        private boolean wordCompare(Word word) {
            if (counts.size() != word.counts.size()) return false;
            if (signature != word.signature) return false;
            Set<Map.Entry<Character, LongAdder>> entries = counts.entrySet();
            Set<Map.Entry<Character, LongAdder>> other = word.counts.entrySet();
            Iterator<Map.Entry<Character, LongAdder>> iterator = entries.iterator();
            Iterator<Map.Entry<Character, LongAdder>> otherIt = other.iterator();
            while (iterator.hasNext() && otherIt.hasNext()) {
                Map.Entry<Character, LongAdder> first = iterator.next();
                Map.Entry<Character, LongAdder> second = otherIt.next();
                if (first.getKey() != second.getKey()
                        || first.getValue().longValue() != second.getValue().longValue()) {
                    return false;
                }
            }
            return true;
        }

        public long getSignature() {
            return signature;
        }

        public String getStr() {
            return str;
        }

        public boolean lazyCompare(String s) {
            if (len != s.length()) {
                return false;
            }
            Word tmp = new Word(s);
            return wordCompare(tmp);
        }
    }

    public Anagrams(ArrayList<String> strings) {
        HashMap<Long, LinkedList<Word>> valueToWords = processDictionary(strings);
        values = new ArrayList<>();
        for (Long value : valueToWords.keySet()) {
            LinkedList<Word> words = valueToWords.get(value);
            splitList(words, values);
        }

    }

    @NotNull
    static HashMap<Long, LinkedList<Word>> processDictionary(ArrayList<String> strings) {
        HashMap<Long, LinkedList<Word>> valueToWords = new HashMap<>();
        for (String s : strings) {
            Word word = new Word(s);
            valueToWords.computeIfAbsent(word.getSignature(), w -> new LinkedList<>()).add(word);
        }
        return valueToWords;
    }

    private void splitList(LinkedList<Word> words, Collection<LinkedList<Word>> res) {
        if (words.isEmpty()) {
            return;
        }
        res.add(words);
        LinkedList<Word> newWords = new LinkedList<>();
        Iterator<Word> iterator = words.iterator();
        Word pivot = iterator.next();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (!pivot.permutation(word)) {
                newWords.add(word);
                iterator.remove();
            }
        }
        splitList(newWords, res);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> test = new ArrayList<>();
//        Path path = Paths.get(".").toAbsolutePath().normalize();
//        System.out.println(path);
        MyIn in = new MyIn("testCase/words");
        int count = 0;
        while (in.hasNext()) {
            test.add(in.next());
//            count++;
//            if (count == 100000) {
//                break;
//            }
        }
        System.out.println("-------Test begin--------");
        long start = System.nanoTime();
        Anagrams anagrams = new Anagrams(test);
        System.out.println(System.nanoTime() - start);
        for (LinkedList<Word> value : anagrams.values) {
            if (value.size() == 1) {
                continue;
            }
            value.forEach(word -> System.out.print(word.str + ";"));
            System.out.println();
        }
    }
}
