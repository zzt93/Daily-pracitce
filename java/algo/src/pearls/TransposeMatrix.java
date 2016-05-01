package pearls;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
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
            for (int j = i + 1; j < n; j++) {
                final int origin = i * n + j;
                final int sym = j * n + i;
                Integer f = matrix.get(origin);
                matrix.set(origin, matrix.get(sym));
                matrix.set(sym, f);
            }
        }
        print(matrix, n);
    }

    private static void print(LinkedList<?> matrix, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix.get(i * n + j)).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
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
            res.add(new IntIJ(content, i / n, i % n, n));
        }
        return res;
    }

    private static void linkedListSort(LinkedList<IntIJ> matrix, int n) {
        Collections.sort(matrix);
        print(matrix, n);
    }

    private static void checkMatrix(LinkedList<Integer> matrix, int n) {
        if (matrix.size() != n * n) {
            throw new IllegalArgumentException();
        }
    }

    private static class IntIJ implements Comparable<IntIJ> {
        private final int content;
        private final int value;

        IntIJ(int content, int r, int c, int n) {
            this.content = content;
            this.value = c * n + r;
        }

        @Override
        public String toString() {
            return "" + content;
        }

        @Override
        public int compareTo(@NotNull IntIJ o) {
            if (o.value < value) {
                return 1;
            } else if (value < o.value) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        correctnessTest();
        timeTest();
    }

    private static void correctnessTest() {
        test(10);
    }

    private static void timeTest() {
        int n = 100;
        test(n);
    }

    private static void test(int n) {
        LinkedList<Integer> matrix = new LinkedList<>();
        for (int i = 0; i < n * n; i++) {
            matrix.add(i);
        }
        long start = System.nanoTime();
        oldTranspose(matrix, n);
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        sortTranspose(matrix, n);
        System.out.println(System.nanoTime() - start);
    }
}
