package methodology.sort;


import java.util.Scanner;

public class CommonElements {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int n = s.nextInt(), n2 = s.nextInt(), n3 = s.nextInt();
            int[] a = new int[n], b = new int[n2], c = new int[n3];
            for (int j = 0; j < n; j++) a[j] = s.nextInt();
            for (int j = 0; j < n2; j++) b[j] = s.nextInt();
            for (int j = 0; j < n3; j++) c[j] = s.nextInt();
            solve(a, b, c);
        }
    }

    private static void solve(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length && k < c.length) {
            if (a[i] == b[j] && b[j] == c[k]) {
                System.out.print(a[i] + " ");
                i++; j++; k++;
            } else {
                int max = Math.max(a[i], Math.max(b[j], c[k]));
                if (a[i] != max) i++;
                if (b[j] != max) j++;
                if (c[k] != max) k++;
            }
        }
        System.out.println();
    }

}
