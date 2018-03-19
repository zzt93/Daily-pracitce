package interview.google.course;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class Disjoint {


    /**
     * fa[i] is i's father or `0` if no father
     */
    private int[] fa;

    public Disjoint(int all, List<int[]> pairs) {
        fa = new int[all+1];
        for (int[] pair : pairs) {
            fa[father(pair[0])] = father(pair[1]);
        }
    }

    public boolean same(int i, int j) {
        return father(i) == father(j);
    }

    private int father(int i) {
        while (fa[i] != 0) {
            i = fa[i];
        }
        return i;
    }

    public static void main(String[] args) {
        Disjoint d = new Disjoint(10, Lists.newArrayList(new int[]{1, 2}, new int[]{2, 4},
                new int[]{3, 6}, new int[]{3, 9}, new int[]{10, 5}, new int[]{7,10}));
        System.out.println(d.same(1, 4));
        System.out.println(d.same(6, 9));
        System.out.println(d.same(5, 7));
        System.out.println(d.same(4, 7));
        System.out.println(d.same(8, 7));
    }
}
