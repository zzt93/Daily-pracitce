package interview.google.course;

import java.util.Arrays;

/**
 * Created by zzt on 3/19/18.
 * <p>
 * <h3></h3>
 */
public class Triangle {

    public static int triangles(int... edges) {
        Arrays.sort(edges);
        int res = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int x = i + 1; x < edges.length; x++) {
                int aim = edges[i] + edges[x];
                int k = Arrays.binarySearch(edges, x + 1, edges.length, aim);
                if (k < 0) k = -k -1;
                // [x+1, k)
                if (k > x + 1) {
                    res += (k - x - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(triangles(1, 2, 3, 4, 5, 6, 7));
        System.out.println(triangles(1, 2, 3, 4, 5));
    }
}
