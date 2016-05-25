package methodology.dynamic;

import java.util.stream.IntStream;

/**
 * Created by zzt on 5/25/16.
 * <p>
 * <h3>Des</h3>
 * <p>A sequence of numbers is called a zig-zag sequence
 * if the differences between successive numbers
 * strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.</p>
 * <p>Given a sequence of integers, sequence,
 * return the length of the longest subsequence of sequence
 * that is a zig-zag sequence. A subsequence is obtained
 * by deleting some number of elements (possibly zero)
 * from the original sequence, leaving the remaining elements in their original order.</p>
 */
public class ZigZag {

    public static int longestZigZag(int[] sequence) {
        if (sequence.length <= 2) {
            return sequence.length;
        }
        int[] longest = new int[sequence.length];
        longest[0] = 1;
        longest[1] = 2;
        for (int i = 2; i < sequence.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                if (sequence[i] != sequence[j] && sequence[j] != sequence[j - 1]
                        && (sequence[i] > sequence[j]) != (sequence[j] > sequence[j - 1])
                        && longest[j] + 1 > longest[i]) {
                    longest[i] = longest[j] + 1;
                }
            }
        }
        return IntStream.of(longest).max().orElse(1);
    }

    public static void main(String[] args) {
        int[] ints = {70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32};
        System.out.println(longestZigZag(ints));
    }
}
