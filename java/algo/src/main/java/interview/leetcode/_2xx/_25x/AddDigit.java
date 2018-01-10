package interview.leetcode._2xx._25x;

/**
 * Created by zzt on 1/9/18.
 * <p>
 * <h3></h3>
 */
public class AddDigit {

    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }
}
