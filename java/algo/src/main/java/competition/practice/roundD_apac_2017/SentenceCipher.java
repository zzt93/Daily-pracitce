package competition.practice.roundD_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by zzt on 10/16/16.
 * <p>
 * <h3>DP: </h3>
 * <p>sub-problem!!!: sentence = words + sentence</p>
 * <p>
 * then the crucial part now is to divide the sentence to word,
 * which can be done by reverse visiting.
 * </p>
 * <code>    e.g. 'isthisa' [this, is, a]
 * at 4: w[1, 4] can in a 'this', can't split;
 * at 6: w[5, 6] make no word, split between 5 and 6
 * <p>
 * </code>
 * <p>
 * <h3>Similar:</h3>
 * <p>pinyin => word: separate pinyin, count possibility</p>
 */
public class SentenceCipher {

    private static long LIMIT = 1000000000 + 7;

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/codejamon-cipher-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        List<Long> res;
        for (int i = 0; i < trail; i++) {

            final int v = in.nextInt();
            final int s = in.nextInt();
            in.nextLine();
            TreeMap<Counter, Integer> vo = new TreeMap<>();
            for (int j = 0; j < v; j++) {
                final String s1 = in.nextLine();
                Counter counter = new Counter(s1);
                vo.compute(counter, (k, val) -> val == null ? 1 : val + 1);
            }
            final ArrayList<String> test = new ArrayList<>(s);
            for (int j = 0; j < s; j++) {
                test.add(in.nextLine());
            }
            res = solve(vo, test);
            StringBuilder sb = new StringBuilder();
            for (Long re : res) {
                sb.append(re % LIMIT).append(" ");
            }
            out.println("Case #" + (i + 1) + ": " + sb.toString());
        }
    }

    private static List<Long> solve(TreeMap<Counter, Integer> vo, ArrayList<String> test) {
        List<Long> res = new ArrayList<>();
        for (String s : test) {
            map.put(s.length(), 1L);
            res.add(count(s.toCharArray(), 0, vo, null));
            map.clear();
        }
        return res;
    }

    private static class Counter implements Comparable<Counter> {
        public static final int SUPER_SET = 1;
        private TreeMap<Character, Integer> map = new TreeMap<>();
        private int count = 0;

        Counter() {
        }

        Counter(String s1) {
            for (char c : s1.toCharArray()) {
                add(c);
            }
        }

        private void add(char c) {
            count++;
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        State canFinish(TreeMap<Counter, Integer> vo) {
            final SortedMap<Counter, Integer> tail = vo.tailMap(this);
            if (tail.isEmpty()) {
                return State.Invalid;
            }
            if (tail.firstKey().compareTo(this) > 0) {
                final Iterator<Counter> iterator = tail.keySet().iterator();
                while (iterator.hasNext()) {
                    final Counter counter = iterator.next();
                    if (counter.compareTo(this) == SUPER_SET) {
                        return State.NotFinish;
                    }
                }
                return State.Invalid;
            } else {
                final Iterator<Counter> iterator = tail.keySet().iterator();
                iterator.next();
                int i = vo.get(this);
                while (iterator.hasNext()) {
                    final Counter counter = iterator.next();
                    final int compareTo = counter.compareTo(this);
                    if (compareTo == SUPER_SET) {
                        return State.More.setCount(i);
                    }
                }
                return State.CanFinish.setCount(i);
            }
        }

        /**
         * @return 0 -- the same
         * 1 -- this is o's super set
         * -1 -- reverse of upper one
         * 2 -- not comparable, this is larger
         * -2 -- reverse of upper one
         */
        @Override
        public int compareTo(Counter o) {
            if (o == this) {
                return 0;
            }
            final Set<Map.Entry<Character, Integer>> e1 = this.map.entrySet();
            final Set<Map.Entry<Character, Integer>> e2 = o.map.entrySet();
            final Set<Character> k1 = this.map.keySet();
            final Set<Character> k2 = o.map.keySet();
            // compare the kinds of char
            final Iterator<Map.Entry<Character, Integer>> smaller;
            TreeMap<Character, Integer> larger;
            boolean k1Ck2 = k1.containsAll(k2);
            boolean k2Ck1 = k2.containsAll(k1);
            if ((e1.size() > e2.size() || count > o.count) && k1Ck2) {
                smaller = e2.iterator();
                larger = this.map;
            } else if ((e2.size() > e1.size() || o.count > count) && k2Ck1) {
                smaller = e1.iterator();
                larger = o.map;
            } else if (!k1Ck2 && !k2Ck1) {
                // none of them contain each other
                final int c = Integer.compare(count, o.count);
                if (c != 0) {
                    return c * 2;
                }
                final int c2 = Integer.compare(e1.size(), e2.size());
                if (c2 != 0) {
                    return c2 * 2;
                }
                // count == o.count && e1.size() == e2.size()
                // e.g. ab vs bc
                final Iterator<Map.Entry<Character, Integer>> it1 = e1.iterator();
                final Iterator<Map.Entry<Character, Integer>> it2 = e2.iterator();
                while (it1.hasNext()) {
                    final Map.Entry<Character, Integer> n1 = it1.next();
                    final Map.Entry<Character, Integer> n2 = it2.next();
                    final int c3 = Integer.compare(n1.getKey(), n2.getKey());
                    if (c3 != 0) {
                        return c3 * 2;
                    }
                    final int c4 = Integer.compare(n1.getValue(), n2.getValue());
                    if (c4 != 0) {
                        return c4 * 2;
                    }
                }
                assert false;
                return 0;
            } else {
                // one map contains the other one, but size equal && count equal
                // aab vs abb, abb vs abb
                assert e1.size() == e2.size();
                final Iterator<Map.Entry<Character, Integer>> it1 = e1.iterator();
                final Iterator<Map.Entry<Character, Integer>> it2 = e2.iterator();
                while (it1.hasNext()) {
                    final Map.Entry<Character, Integer> n1 = it1.next();
                    final Map.Entry<Character, Integer> n2 = it2.next();
                    final int c4 = Integer.compare(n1.getValue(), n2.getValue());
                    if (c4 != 0) {
                        return -c4 * 2;
                    }
                }
                return 0;
            }

            while (smaller.hasNext()) {
                final Map.Entry<Character, Integer> next = smaller.next();
                if (larger.get(next.getKey()) < next.getValue()) {
                    return larger == this.map ? 2 : -2;
                }
            }
            return larger == this.map ? 1 : -1;

        }
    }

    private enum State {
        NotFinish, CanFinish, More, Invalid;

        int count;

        State setCount(int i) {
            count = i;
            return this;
        }
    }

    private static Map<Integer, Long> map = new HashMap<>(4000);

    private static long count(char[] s, int start, TreeMap<Counter, Integer> vo, Counter counter) {
        Counter c = counter;
        int key = start;
        if (counter == null) {
            c = new Counter();
        } else {
            key = start - c.count;
        }
        if (map.containsKey(key)) { // include the base case
            return map.get(key);
        }


        long res = 0;
        for (int i = start; i < s.length; i++) {
            c.add(s[i]);
            final State state = c.canFinish(vo);
            switch (state) {
                case NotFinish:
                    continue;
                case CanFinish:
                    res = count(s, i + 1, vo, null) * state.count;
                    map.put(key, res);
                    return res;
                case More:
                    res += count(s, i + 1, vo, null) * state.count;
                    res += count(s, i + 1, vo, c);
                    map.put(key, res);
                    return res;
                case Invalid:
                    return 0;
            }
        }
        return res;
    }

}

/*
#include <cstdio>
#include <map>
#include <vector>

using namespace std;

const int N = 4001;
const int mod = 1000000007;

map<vector<int>, int> c;
char s[N];
int dp[N];
int n, m;

inline void update(int &a, long long b) {
	a = (a + b) % mod;
}

int main() {
	int t, tt;
	scanf("%d", &t);
	for (tt = 1; tt <= t; tt++) {
		scanf("%d%d", &n, &m);
		c.clear();
		for (int i = 0; i < n; i++) {
			vector<int> a(26);
			scanf("%s", s);
			for (int j = 0; s[j] != '\0'; j++) a[s[j] - 'a']++;
			c[a]++;
		}
		printf("Case #%d:", tt);
		for (int i = 0; i < m; i++) {
			scanf("%s", s);
			int ls = strlen(s);
			dp[0] = 1;
			for (int j = 0; j < ls; j++) {
				dp[j + 1] = 0;
				vector<int> a(26);
				for (int k = 0; k < 20 && j - k >= 0; k++) {
					a[s[j - k] - 'a']++;
					map<vector<int>, int>::iterator it = c.find(a);
					if (it != c.end()) update(dp[j + 1], (long long)dp[j - k] * it->second);
				}
			}
			printf(" %d", dp[ls]);
		}
		printf("\n");
	}
	return 0;
}

 */