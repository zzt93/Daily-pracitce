package basic;

import java.util.Arrays;

/**
 * Created by zzt on 8/5/16.
 * <p>
 * <h3></h3>
 */
public class LearnChar {

    public static void main(String[] args) {
        char c = '测';
        String s = "\u10437";
        String s1 = "\uD801\uDC02";
        int c1 = 0x10437;
        System.out.println(Character.isLetter(c1));
        System.out.println(Integer.toHexString(c));
        System.out.println(s + ": " + s.length());
        System.out.println(s1 + ": " + s1.length());
        System.out.println(Arrays.toString(s.toCharArray()));
        System.out.println(Arrays.toString(s1.toCharArray()));

    }

}

