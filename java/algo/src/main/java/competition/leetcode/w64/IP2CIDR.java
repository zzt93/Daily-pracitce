package competition.leetcode.w64;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 12/24/17.
 * <p>
 * <h3></h3>
 */
public class IP2CIDR {

    public List<String> ipToCIDR(String ip, int range) {
        long n = fromIp(ip);
        List<String> res = new LinkedList<>();
        recur(res, n, n + range, 0, 1L << 32);
        return res;
    }

    private void recur(List<String> res, long l, long r, long ll, long rr) {
        if (l == ll && r == rr) {
            res.add(toIp(l) + "/" + (33 - log2(rr - ll)));
            return;
        }
        long mid = (ll + rr) / 2;
        if (l >= mid) recur(res, l, r, mid, rr);
        else if (r <= mid) recur(res, l, r, ll, mid);
        else {
            recur(res, l, mid, ll, mid);
            recur(res, mid, r, mid, rr);
        }
    }

    private int log2(long t) {
        int c = 0;
        while (t != 0) {
            t >>= 1;
            c++;
        }
        return c;
    }

    private long fromIp(String ip) {
        long res = 0;
        for (String s : ip.split("\\.")) {
            res = (res << 8) + Long.parseLong(s);
        }
        return res;
    }

    private static final int mask = 0xff;

    private String toIp(long ip) {
//        StringJoiner res = new StringJoiner(".");
//        for (int i = 3; i >= 0; i--) {
//            res.add("" + ((ip >>> i * 8) & mask));
//        }
        StringBuilder res = new StringBuilder();
        res.append((ip >>> 24) & mask);
        for (int i = 2; i >= 0; i--) {
            res.append(".").append((ip >>> i * 8) & mask);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        IP2CIDR c = new IP2CIDR();
        System.out.println(c.ipToCIDR("0.0.0.7", 10));
        System.out.println(c.ipToCIDR("0.0.0.8", 10));
        System.out.println(c.ipToCIDR("255.0.0.7", 10));
        System.out.println(c.ipToCIDR("255.0.0.7", 100));
    }
}
