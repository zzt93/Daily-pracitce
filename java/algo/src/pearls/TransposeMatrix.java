package pearls;

import org.jetbrains.annotations.NotNull;

import java.util.*;

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

    public static void oldTranspose(List<Integer> matrix, int n, boolean print) {
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
        if (print) {
            print(matrix, n);
        }
    }

    public static void oldTransposeOpt(List<Integer> matrix, int n, boolean print) {
        checkMatrix(matrix, n);
        final ListIterator<Integer> it = matrix.listIterator();
        for (int i = 0; i < n; i++) {
            for (int i1 = 0; i1 < i + 1; i1++) {
                it.next();
            }
            for (int j = i + 1; j < n; j++) {
                final int sym = j * n + i;
                Integer f = it.next();
                it.set(matrix.get(sym));
                matrix.set(sym, f);
            }
        }
        if (print) {
            print(matrix, n);
        }
    }

    private static void print(List<?> matrix, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix.get(i * n + j)).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }


    private static void sortTranspose(List<Integer> matrix, int n, boolean print) {
        checkMatrix(matrix, n);
        Collections.sort(process(matrix, n));
        if (print) {
            print(matrix, n);
        }
    }

    private static List<IntIJ> process(List<Integer> matrix, int n) {
        LinkedList<IntIJ> res = new LinkedList<>();
        Iterator<Integer> iterator = matrix.iterator();
        for (int i = 0; i < matrix.size(); i++) {
            Integer content = iterator.next();
            res.add(new IntIJ(content, i / n, i % n, n));
        }
        return res;
    }

    private static void checkMatrix(List<Integer> matrix, int n) {
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
//        correctnessTest();
                timeTest();
    }

    private static void correctnessTest() {
        testList(10, true);
//        testArray(10, true);
    }

    private static void timeTest() {
        int n = 200;
        System.out.println("-----------list------------");
        testList(n, false);
        System.out.println("-----------array------------");
        testArray(n * 10, false);
    }

    private static void testList(int n, boolean print) {
        LinkedList<Integer> matrix = new LinkedList<>();
        test(n, matrix, print);
    }

    private static void testArray(int n, boolean print) {
        ArrayList<Integer> matrix = new ArrayList<>(n);
        test(n, matrix, print);
    }

    private static void test(int n, List<Integer> matrix, boolean print) {
        final int size = n * n;
        for (int i = 0; i < size; i++) {
            matrix.add(i);
        }
        System.out.println("index transpose");
        long start = System.nanoTime();
        oldTranspose(matrix, n, print);
        System.out.println(System.nanoTime() - start);

        System.out.println("iterator transpose");
        start = System.nanoTime();
        oldTransposeOpt(matrix, n, print);
        System.out.println(System.nanoTime() - start);

        System.out.println("sort transpose");
        start = System.nanoTime();
        sortTranspose(matrix, n, print);
        System.out.println(System.nanoTime() - start);
    }

}
