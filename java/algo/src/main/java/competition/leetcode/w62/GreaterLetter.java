package competition.leetcode.w62;


import java.util.Arrays;

/**
 * Created by zzt on 12/10/17.
 * <p>
 * <h3></h3>
 */
public class GreaterLetter {

    public char nextGreatestLetter(char[] letters, char target) {
        int i = Arrays.binarySearch(letters, target);
        while (i >= 0) {
            i = Arrays.binarySearch(letters, i + 1, letters.length, target);
        }
        i = -i - 1;
        if (i == letters.length) {
            i = 0;
        }
        return letters[i];
    }

    public static void main(String[] args) {
        GreaterLetter g = new GreaterLetter();
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'd'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'k'));
        System.out.println(g.nextGreatestLetter(new char[]{'c', 'c', 'n'}, 'c'));
        System.out.println(g.nextGreatestLetter(new char[]{'a', 'z', 'z'}, 'z'));
        System.out.println(g.nextGreatestLetter(new char[]{'a', 'y','y','z', 'z'}, 'y'));
    }
}
