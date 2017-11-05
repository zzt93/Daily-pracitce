package competition.leetcode.w57;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zzt on 11/5/17.
 * <p>
 * <h3></h3>
 */
public class MergeAccounts {

    private static class User {
        private String name;
        private Set<String> es = new TreeSet<>();

        public User(String name) {
            this.name = name;
        }

        public User addAcc(List<String> account) {
            for (int i = 1; i < account.size(); i++) {
                es.add(account.get(i));
            }
            return this;
        }

        public List<String> toStr() {
            LinkedList<String> res = new LinkedList<>(es);
            res.addFirst(name);
            return res;
        }

        public void addAcc(User user) {
            es.addAll(user.es);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, User> email2user = new HashMap<>();
        List<User> res = new ArrayList<>();
        for (List<String> account : accounts) {
            User user = null;
            boolean contain = false;
            for (int i = 1; i < account.size(); i++) {
                if (email2user.containsKey(account.get(i))) {
                    contain = true;
                    User tmp = email2user.get(account.get(i));
                    if (user == null) {
                        user = tmp;
                    } else {
                        if (user != tmp) {
                            res.remove(tmp);
                            for (String e : tmp.es) {
                                email2user.put(e, user);
                            }
                            user.addAcc(tmp);
                        }
                    }
                }
            }
            if (contain) {
                user.addAcc(account);
            } else {
                user= new User(account.get(0)).addAcc(account);
                res.add(user);
            }
            for (int i = 1; i < account.size(); i++) {
                email2user.put(account.get(i), user);
            }
        }
        return res.stream().map(User::toStr).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        MergeAccounts m = new MergeAccounts();
        System.out.println(m.accountsMerge(Lists.newArrayList(Lists.newArrayList("Hanzo",
                "Hanzo2@m.co", "Hanzo3@m.co"), Lists.newArrayList("Hanzo", "Hanzo4@m.co",
                "Hanzo5@m.co"), Lists.newArrayList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co"), Lists
                .newArrayList("Hanzo", "Hanzo3@m.co", "Hanzo4@m.co"), Lists.newArrayList("Hanzo",
                "Hanzo7@m.co", "Hanzo8@m.co"), Lists.newArrayList("Hanzo", "Hanzo1@m.co",
                "Hanzo2@m.co"), Lists.newArrayList("Hanzo", "Hanzo6@m.co", "Hanzo7@m.co"), Lists
                .newArrayList("Hanzo", "Hanzo5@m.co", "Hanzo6@m.co"))));
        System.out.println(m.accountsMerge(Lists.newArrayList(Lists.newArrayList("Alex", "Alex5@m" +
                ".co", "Alex4@m.co", "Alex0@m.co"), Lists.newArrayList("Ethan", "Ethan3@m.co",
                "Ethan3@m.co", "Ethan0@m.co"), Lists.newArrayList("Kevin", "Kevin4@m.co",
                "Kevin2@m.co", "Kevin2@m.co"), Lists.newArrayList("Gabe", "Gabe0@m.co", "Gabe3@m" +
                ".co", "Gabe2@m.co"), Lists.newArrayList("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co"))));
        System.out.println(m.accountsMerge(Lists.newArrayList(Lists.newArrayList("David",
                "David0@m.co", "David5@m.co", "David8@m.co"), Lists.newArrayList("David",
                "David1@m.co", "David8@m.co"))));
        System.out.println(m.accountsMerge(Lists.newArrayList(Lists.newArrayList("David",
                "David0@m.co", "David1@m.co"), Lists.newArrayList("David", "David3@m.co",
                "David4@m.co"), Lists.newArrayList("David", "David4@m.co", "David5@m.co"), Lists
                .newArrayList("David", "David2@m.co", "David3@m.co"), Lists.newArrayList("David",
                "David1@m.co", "David2@m.co"))));
        System.out.println(m.accountsMerge(Lists.newArrayList(
                Lists.newArrayList("John", "johnsmith@mail.com", "john00@mail.com"), Lists.newArrayList("John",
                        "johnnybravo@mail.com"), Lists.newArrayList("John", "johnsmith@mail.com",
                        "john_newyork@mail.com"))));

    }

}
