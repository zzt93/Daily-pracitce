package trie;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by zzt on 4/7/16.
 * <p>
 * Usage:
 */
public class MappingTrieTest {

    private MappingTrie<Character, String> trie;
    private ArrayList<String> tests;

    @org.junit.Before
    public void setUp() throws Exception {
        trie = new MappingTrie<>('\0', '\0');
        String allow = "allow";
        String deny = "deny";

        String abca = "abca";
        String abcd = "abcd";
        String abce = "abce";
        String abe = "abe";

        addTo(abca, allow);
        addTo(abcd, deny);
        addTo(abce, allow);
        addTo(abe, deny);
        tests = new ArrayList<>();
        tests.add(abca);
        tests.add(abca + "a");
        tests.add(abcd);
        tests.add(abce);
        tests.add(abe);
        tests.add(abe + "f");
        tests.add("abcf");
    }

    @Test
    public void testIterator() {
        for (String test : tests) {
            MappingIterator<Character, String> it = trie.getMappingIterator();
            for (char c : test.toCharArray()) {
                if (it.hasChild(c)) {
                    it.move(c);
                } else {
                    break;
                }
            }
            if (it.canExit()) {
                System.out.println(it.getValue());
            } else {
                System.out.println("allow");
            }
        }
    }

    private void addTo(String key, String value) {
        ArrayList<Character> abc = new ArrayList<>();
        for (char c : key.toCharArray()) {
            abc.add(c);
        }
        trie.add(abc, value);
    }
}