package pearls;

import java.io.IOException;

/**
 * Created by zzt on 4/22/16.
 * <p>
 * <h3>Rotation usage</h3>
 * <li>swapping adjacent blocks of memory of unequal size</li>
 * <h3>Relationship with reverse</h3>
 * <li>rotation can be implemented by reverse: ab -- a^r b^r -- (a^r b^r)^r -- ba</li>
 * <li>reverse can also be implemented by rotation: ab -- ba -- recursive with small part
 * with `b` and `a`</li>
 */
public class RotateArray<T> {

    public static final int N = 3000;
    private static final int SIZE = 10000;

    enum Direction {
        LEFT {
            @Override
            public Direction reverse() {
                return RIGHT;
            }
        }, RIGHT {
            @Override
            public Direction reverse() {
                return LEFT;
            }
        },;

        public abstract Direction reverse();
    }

    static class Rotation {
        private int dis;
        private Direction dir;

        public Rotation(int dis, Direction dir) {
            this.dis = dis;
            this.dir = dir;
        }

        public void setDirAndDis(Direction dir, int dis) {
            this.dis = dis;
            this.dir = dir;
        }

        public int getDis() {
            return dis;
        }

        public Direction getDir() {
            return dir;
        }

        public Rotation minimize(int len) {
            assert dis <= len;
            if (len < dis * 2) {
                return new Rotation(len - dis, dir.reverse());
            }
            return this;
        }
    }

    private T[] array;

    public RotateArray(T[] array) {
        this.array = array;
    }

    public T[] rotate(Direction direction, int n) {
        Rotation rotation = minimizeDistance(array.length, new Rotation(n, direction));
        jugglingRotate(rotation, array);
        return array;
    }

    public T[] rotate2(Direction direction, int n) {
        Rotation rotation = minimizeDistance(array.length, new Rotation(n, direction));
        reverseRotate(rotation, array);
        return array;
    }

    public T[] rotate3(Direction direction, int n) {
        Rotation rotation = minimizeDistance(array.length, new Rotation(n, direction));
        dynamicRotate(rotation, array);
        return array;
    }

    /**
     * @param rotation rotation request
     * @param array    input
     *
     * @implNote ab -> ba
     * <li>a^r b^r</li>
     * <li>(a^r b^r)^r = ba</li>
     */
    private void reverseRotate(Rotation rotation, T[] array) {
        int len = rotation.dis;
        int length = array.length;
        if (rotation.getDir() == Direction.RIGHT) {
            reverse(array, 0, length - len);
            reverse(array, length - len, len);
            reverse(array, 0, length);
        } else {
            reverse(array, 0, len);
            reverse(array, len, length - len);
            reverse(array, 0, length);
        }
    }

    private void reverse(T[] array, int start, int len) {
        reverseFor(array, start, len);
//        if (len <= 1) {
//            return;
//        }
//        int last = start + len - 1;
//        swap(array, start, last);
//        reverse(array, start + 1, len - 2);
    }

    private void reverseFor(T[] array, int start, int len) {
        for (int i = 0; i < len / 2; i++) {
            int f = i + start;
            int last = start + len - i - 1;
            swap(array, f, last);
        }
    }

    private static <T> void swap(T[] array, int t, int i) {
        T tmp = array[t];
        array[t] = array[i];
        array[i] = tmp;
    }

    /**
     * @param rotation rotation request
     * @param array    input
     *
     * @implNote solve array of N by solve array of N-n
     */
    private void dynamicRotate(Rotation rotation, T[] array) {
        recursiveRotate(array, 0, array.length, rotation);
    }

    static <T> void recursiveRotate(T[] array, int arrayStart, int arrayEnd, Rotation rotation) {
        int len = arrayEnd - arrayStart;
        Rotation real = rotation.minimize(len);
        if (real.getDis() <= 0) {
            return;
        }
        blockSwap(array, arrayStart, arrayEnd - real.dis, real.dis);
        int nextS;
        int nextE;
        if (real.getDir() == Direction.LEFT) {
            nextS = arrayStart;
            nextE = arrayEnd - real.getDis();
        } else {
            nextS = arrayStart + real.getDis();
            nextE = arrayEnd;
        }
        recursiveRotate(array, nextS, nextE, real);
    }


    private static <T> void blockSwap(T[] array, int f, int s, int len) {
        assert f + len <= s;
        for (int i = f; i < f + len; i++) {
            swap(array, i, s);
            s++;
        }
    }

    /**
     * @param rotation rotate specification
     * @param array    input
     *
     * @implNote classify the number into n types according to their reminder
     * when divide to n
     */
    private void jugglingRotate(Rotation rotation, T[] array) {
        int length = array.length;
        int n = GCD(length, rotation.dis);
        int dis = rotation.dis;
        if (rotation.dir == Direction.LEFT) {
            for (int i = 0; i < n; i++) {
                T first = array[i];
                int j;
                for (j = i; ; j += dis) {
                    if (j > length) {
                        j -= length;
                    }
                    if (j == i) {
                        break;
                    }
                    array[j] = array[j + dis];
                }
                array[j] = first;
            }
        } else {
            for (int i = 0; i < n; i++) {
                T last = array[length - 1 - i];
                int j;
                for (j = array.length - 1 - i; ; j -= dis) {
                    if (j < 0) {
                        j += length;
                    }
                    if (j == length - 1 - i) {
                        break;
                    }
                    array[j] = array[j - dis];
                }
                array[j] = last;
            }
        }
    }

    //    private T[] leftRotate(T arr[], int d) {
    //        int n = arr.length;
    //        int i, j, k;
    //        T temp;
    //        int gcd = GCD(d, n);
    //        for (i = 0; i < gcd; i++) {
    //            temp = arr[i];
    //            j = i;
    //            while (true) {
    //                k = j + d;
    //                if (k >= n)
    //                    k = k - n;
    //                if (k == i)
    //                    break;
    //                arr[j] = arr[k];
    //                j = k;
    //            }
    //            arr[j] = temp;
    //        }
    //        return arr;
    //    }

    private int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }

    private Rotation minimizeDistance(int length, Rotation rotation) {
        int n = rotation.getDis();
        while (n >= length) {
            n -= length;
        }
        Direction dir = rotation.getDir();
        if (n * 2 > length) {
            dir = dir.reverse();
            n = length - n;
        }
        return new Rotation(n, dir);
    }

    public static void main(String[] args) throws IOException {
        System.in.read();

        for (int i = 100; i < SIZE; i += 100) {
            test(i, i / 3);
        }
    }

    private static void test(int size, int n) {
        Integer[] integers = new Integer[size];
        for (int i = 0; i < size; i++) {
            integers[i] = i + 1;
        }
        RotateArray<Integer> rotateArray = new RotateArray<>(integers);
        warmUp(rotateArray);
        System.out.println(size + ":");
        long start = System.nanoTime();
        rotateArray.rotate(Direction.RIGHT, n);
        System.out.println("juggling " + (System.nanoTime() - start));
        //        System.out.println(Arrays.toString(rotateArray.array));

        start = System.nanoTime();
        rotateArray.rotate2(Direction.RIGHT, n);
        System.out.println("reverse " + (System.nanoTime() - start));
//                System.out.println(Arrays.toString(rotateArray.array));

        start = System.nanoTime();
        rotateArray.rotate3(Direction.RIGHT, n);
        System.out.println("dynamic " + (System.nanoTime() - start));
        //        System.out.println(Arrays.toString(rotateArray.array));

        System.out.println("------------");
    }

    private static void warmUp(RotateArray<Integer> rotateArray) {
        rotateArray.rotate(Direction.LEFT, 200);
    }
}
