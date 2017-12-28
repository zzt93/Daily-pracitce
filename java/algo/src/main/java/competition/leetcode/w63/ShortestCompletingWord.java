package competition.leetcode.w63;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by zzt on 12/17/17.
 * <p>
 * <h3></h3>
 */
public class ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        String res = "";
        int min = 20;
        HashMap<Character, Integer> count = getCharacterIntegerHashMap(licensePlate);
        OUT:
        for (String word : words) {
            if (word.length() >= min) continue;
            HashMap<Character, Integer> map = getCharacterIntegerHashMap(word);
            for (Character c : count.keySet()) {
                if (!map.containsKey(c) || map.get(c) < count.get(c)) {
                    continue OUT;
                }
            }
            min = word.length();
            res = word;
        }
        return res;
    }

    @NotNull
    private HashMap<Character, Integer> getCharacterIntegerHashMap(String licensePlate) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerCase = Character.toLowerCase(c);
                if (count.containsKey(lowerCase)) {
                    count.put(lowerCase, count.get(lowerCase) + 1);
                } else {
                    count.put(lowerCase, 1);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ShortestCompletingWord s = new ShortestCompletingWord();
        System.out.println(s.shortestCompletingWord("1s3 456", new String[]{"looks", "pest", "stew", "show"}));
        System.out.println(s.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));

    }
}
