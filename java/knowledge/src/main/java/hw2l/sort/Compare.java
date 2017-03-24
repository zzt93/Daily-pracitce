package hw2l.sort;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by zzt on 3/9/15.
 */
public class Compare {

    static final int SIZE = 100000;
    Integer[] toSort = new Integer[SIZE];
    ArrayList<Integer> arrayList = new ArrayList<>(SIZE);
    int[] sort = new int[SIZE];

    public Compare() {
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
            arrayList.add(sort[i]);
        }
        assert i == SIZE;
    }

    public void parallel() {
        System.out.println("----------Integer parallel Arrays.sort------:");
        long start = System.nanoTime();
        Arrays.parallelSort(toSort);
        System.out.println(System.nanoTime() - start);
    }

    public void normal() {
        System.out.println("----------Integer Arrays.sort------:");
        long start = System.nanoTime();
        Arrays.sort(toSort);
        System.out.println(System.nanoTime() - start);
    }

    public void sortInt() {
        System.out.println("----------Int parallel Arrays.sort------:");
        long start = System.nanoTime();
        Arrays.parallelSort(sort);
        System.out.println(System.nanoTime() - start);
    }

    public void sortIntNo() {
        System.out.println("----------Int Arrays.sort------:");
        long start = System.nanoTime();
        Arrays.sort(sort);
        System.out.println(System.nanoTime() - start);
    }

    public void sortArraylist() {
        System.out.println("----------Integer Collections.sort------:");
        long start = System.nanoTime();
        Collections.sort(arrayList);
        System.out.println(System.nanoTime() - start);
    }

    public void ArraylistToArraySort() {
        System.out.println("----------Integer to int Arrays.sort------:");
        long start = System.nanoTime();
        int[] tmp = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            tmp[i] = arrayList.get(i);
        }
        Arrays.sort(tmp);
        System.out.println(System.nanoTime() - start);
    }

    public static void main(String[] args) {
        new Compare().sortIntNo();
        System.out.println("-----------warm up------------");
        new Compare().sortArraylist();
        new Compare().normal();
        new Compare().sortInt();
        new Compare().parallel();
        new Compare().sortIntNo();
        new Compare().ArraylistToArraySort();
    }
}
