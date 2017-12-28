package competition.practice.round1B2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zzt on 5/7/16.
 * <p>
 * Usage:
 */
public class PhoneNumber {

    private static LinkedHashMap<Character, Integer> unique = new LinkedHashMap<>();
    private static String[] numbers = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
            "SEVEN", "EIGHT", "NINE"};

    static {
        unique.put('Z', 0);
        unique.put('U', 4);
        unique.put('X', 6);
        unique.put('G', 8);
        unique.put('R', 3);
        unique.put('S', 7);
        unique.put('T', 2);
        unique.put('V', 5);
        unique.put('O', 1);
        unique.put('I', 9);
    }

    public static String phoneNumber(String str) {
        HashMap<Character, LongAdder> count = new HashMap<>();
        for (char c : str.toCharArray()) {
            count.computeIfAbsent(c, ch -> new LongAdder()).increment();
        }
        ArrayList<Integer> tmp = new ArrayList<>();
        for (char c : unique.keySet()) {
            if (count.containsKey(c)) {
                final LongAdder times = count.get(c);
                final Integer in = unique.get(c);
                for (int i = 0; i < times.intValue(); i++) {
                    tmp.add(in);
                }
                for (char ch : numbers[in].toCharArray()) {
                    final LongAdder old = count.get(ch);
                    final int x = old.intValue() - times.intValue();
                    if (x == 0) {
                        count.remove(ch);
                        continue;
                    }
                    final LongAdder current = new LongAdder();
                    current.add(x);
                    count.replace(ch, current);
                }
            }
        }
        Collections.sort(tmp);
        StringBuilder sb = new StringBuilder(tmp.size());
        for (Integer integer : tmp) {
            sb.append(integer);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/phone-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        String res;
        for (int i = 0; i < trail; i++) {
            res = phoneNumber(in.nextLine());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }
}
