package interview.google.course;

import java.util.Iterator;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class MyIt implements Iterator<Integer> {
    private int[] n;
    private int index = 0;
    private int now = 0;

    public MyIt(int[] nums) {
        n = nums;
    }

    public boolean hasNext() {
        return index < n.length - 2 || (index == n.length - 2 && now < n[index]);
    }

    public Integer next() {
        // pre: hasNext()
        if (n[index] > now) {
            now++;
            return n[index + 1];
        } else {
            now = 1;
            index += 2;
            return n[index + 1];
        }
        // post: n[index] - now: n[index+1]
    }

    public static void main(String[] args) {
        MyIt myIt = new MyIt(new int[]{2, 1, 4, 3, 3, 2});
        while (myIt.hasNext()) {
            System.out.println(myIt.next());
        }
    }
}

