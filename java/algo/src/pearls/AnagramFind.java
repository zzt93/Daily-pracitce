package pearls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/28/16.
 * <h3>Problem: find element</h3>
 * <p>
 * Given a dictionary and a word, find this word's anagram:
 * <ul>
 * cases:
 * <li>dictionary can't be preprocessed</li>
 * <li>dictionary can be preprocessed</li>
 * <li>find how many times</li>
 * </ul>
 * </p>
 */
public class AnagramFind {
    private HashMap<Long, LinkedList<Anagrams.Word>> dict;

    public AnagramFind(ArrayList<String> origin) {
        dict = Anagrams.processDictionary(origin);
    }

    public LinkedList<String> find(String target) {
        Anagrams.Word aim = new Anagrams.Word(target);
        LinkedList<Anagrams.Word> words = dict.get(aim.getSignature());
        return words
                .stream()
                .filter(aim::permutation)
                .map(Anagrams.Word::getStr)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static LinkedList<String> findOnce(ArrayList<String> dictionary, String target) {
        Anagrams.Word word = new Anagrams.Word(target);
        return dictionary.stream().filter(word::lazyCompare).collect(Collectors.toCollection(LinkedList::new));
    }

    public static void main(String[] args) {

    }
}
