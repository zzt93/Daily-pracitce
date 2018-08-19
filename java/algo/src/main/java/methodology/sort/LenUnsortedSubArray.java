package methodology.sort;


import java.util.*;
import java.lang.*;


public class LenUnsortedSubArray {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = s.nextInt();
            }
            solve(a);
        }
    }

    private static void solve(int[] a) {
        // System.out.println(Arrays.toString(a));
        int l = 0, r = 0;
        int maxI = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[maxI] > a[i]) {
                int sorted;
                if (l == r) {
                    sorted = maxI;
                } else {
                    sorted = l;
                }
                int x = Arrays.binarySearch(a, 0, sorted, a[i]);
                if (x < 0) x = -x - 1;
                else x++;
                l = Math.min(x, sorted);
                r = i;
            } else {
                maxI = i;
            }
        }
        System.out.println(l + " " + r);
    }


}
