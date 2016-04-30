package pearls;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zzt on 4/30/16.
 * <p>
 * <h3>how to efficient transpose a very large matrix(n*n) on the tape</h3>
 * <li>tape is not randomly accessed which can be simulate by linkedList</li>
 * <li>in essence, the problem is to change the order of input -- sort/swap</li>
 * <h3>Solution</h3>
 * <li>sort by row + column * n</li>
 * <li>sort first by column, then sort by row -- radix sort</li>
 */
public class TransposeMatrix {

    public static void oldTranspose(LinkedList<Integer> matrix, int n) {
        checkMatrix(matrix, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Integer integer = matrix.get(i * n + j);
                matrix.set(j * n + i, integer);
            }
        }
        print(matrix, n);
    }

    private static void print(LinkedList<?> matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix.get(i * n + j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        correctnessTest();
        //        timeTest();
    }

    private static void correctnessTest() {
        test(10);
    }

    private static void timeTest() {
        int n = 1000;
        test(n);
    }

    private static void test(int n) {
        LinkedList<Integer> matrix = new LinkedList<>();
        for (int i = 0; i < n * n; i++) {
            matrix.add(i);
        }
        oldTranspose(matrix, n);
        sortTranspose(matrix, n);
    }

    private static void sortTranspose(LinkedList<Integer> matrix, int n) {
        checkMatrix(matrix, n);
        linkedListSort(process(matrix, n), n);
    }

    private static LinkedList<IntIJ> process(LinkedList<Integer> matrix, int n) {
        LinkedList<IntIJ> res = new LinkedList<>();
        Iterator<Integer> iterator = matrix.iterator();
        for (int i = 0; i < matrix.size(); i++) {
            Integer content = iterator.next();
            res.add(new IntIJ(content, i / n, i % n));
        }
        return res;
    }

    private static void linkedListSort(LinkedList<IntIJ> matrix, int n) {

        print(matrix, n);
    }

    private static void checkMatrix(LinkedList<Integer> matrix, int n) {
        if (matrix.size() != n * n) {
            throw new IllegalArgumentException();
        }
    }

    private static class IntIJ implements Comparable<IntIJ> {
        private final int content;
        private final int r;
        private final int c;

        public IntIJ(int content, int r, int c) {
            this.content = content;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "" + content;
        }

        @Override
        public int compareTo(IntIJ o) {
            if (o.content < content) {
                return 1;
            } else if (content < o.content) {
                return -1;
            }
            return 0;
        }
    }
}
