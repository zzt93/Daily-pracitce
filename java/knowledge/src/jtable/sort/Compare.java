package jtable.sort;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zzt on 3/9/15.
 */
public class Compare {

    static final int SIZE = 100000;
    Integer[] toSort = new Integer[SIZE];
    int[] sort = new int[SIZE];

    Compare() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(new File("IntegerArray.txt"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i;
        for (i = 0; scanner.hasNext(); i++) {
            toSort[i] = scanner.nextInt();
            sort[i] = toSort[i];
        }
        assert i == SIZE;
    }
    public void parallel() {
        System.out.print("----------Integer parellel jtable.sort------:");
        long start = System.nanoTime();
        Arrays.parallelSort(toSort);
        System.out.println(System.nanoTime() - start);
    }

    public void normal() {
        System.out.print("----------Integer jtable.sort------:");
        long start = System.nanoTime();
        Arrays.sort(toSort);
        System.out.println(System.nanoTime() - start);
    }

    public void sortInt() {
        System.out.print("----------Int parellel jtable.sort------:");
        long start = System.nanoTime();
        Arrays.parallelSort(sort);
        System.out.println(System.nanoTime() - start);
    }

    public void sortIntNo() {
        System.out.print("----------Int jtable.sort------:");
        long start = System.nanoTime();
        Arrays.sort(sort);
        System.out.println(System.nanoTime() - start);
    }

    public static void main(String[] args) {
        new Compare().sortIntNo();
        //
        new Compare().normal();
        new Compare().sortInt();
        new Compare().parallel();
        new Compare().sortIntNo();
    }
}
