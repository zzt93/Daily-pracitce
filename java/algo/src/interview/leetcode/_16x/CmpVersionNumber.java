package interview.leetcode._16x;

/**
 * Created by zzt on 11/14/17.
 * <p>
 * <h3></h3>
 */
public class CmpVersionNumber {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int vl = Math.min(v1.length, v2.length);
        for (int i = 0; i < vl; i++) {
            long v1m = Long.parseLong(v1[i]);
            long v2m = Long.parseLong(v2[i]);
            if (v1m > v2m) {
                return 1;
            } else if (v1m < v2m) {
                return -1;
            }
        }
        long v1f = 0, v2f = 0;
        if (v1.length < v2.length) {
            v2f = Long.parseLong(v2[vl++]);
            while (v1f == v2f && vl < v2.length) {
                v2f = Long.parseLong(v2[vl++]);
            }
        } else if (v1.length > v2.length) {
            v1f = Long.parseLong(v1[vl++]);
            while (v1f == v2f && vl < v1.length) {
                v1f = Long.parseLong(v1[vl++]);
            }
        }
        return Long.compare(v1f, v2f);
    }

    public static void main(String[] args) {
        CmpVersionNumber c = new CmpVersionNumber();
        System.out.println(c.compareVersion("1", "0"));
        System.out.println(c.compareVersion("1.0.0.1", "1.0.0"));
        System.out.println(c.compareVersion("1.0.0.1", "1.0"));
        System.out.println(c.compareVersion("1", "1.0.1"));
        System.out.println(c.compareVersion("1.1.1", "1.1.0"));
    }
}
