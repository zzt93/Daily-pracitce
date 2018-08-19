
package methodology.sort;


import java.util.*;
import java.lang.*;


public class SortInRange {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = s.nextInt();
            }
            bucket(a);
        }
    }

	
	private static void bucket(int[] a) {
	    int n = a.length;
	    LinkedList[] buckets = new LinkedList[n];
	    for (int i = 0; i < n; i++) {
	        buckets[i] = new LinkedList<>();
	    } 
	    for (int i: a) {
	        buckets[i/n].add(i);
	    }
	    for (List<Integer> l: buckets) {
	        l.sort(null);
	        for (int i: l) System.out.print(i + " ");
	    }
	    System.out.println();
	}

    private static void radix(int[] a) {
        int[] t = counting(a, 1);
        t = counting(t, a.length);
        for (int i: t) System.out.print(i + " ");
        System.out.println();
    }
    
    private static int[] counting(int[] a, int d) {
        int n = a.length;
        int[] count = new int[n];
        for (int i: a) {
            count[i/d%n]++;
        }
        for (int i = 1; i < n; i++) {
            count[i] += count[i-1];
        }
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            res[count[a[i]/d%n]-1] = a[i];
            count[a[i]/d%n]--;
        }
        return res;
    }

}

