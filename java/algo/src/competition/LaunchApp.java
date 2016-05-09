package competition;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by zzt on 5/9/16.
 * <p>
 * Usage:
 */
public class LaunchApp {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/launch-app.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        ArrayList<Integer> res;
        for (int i = 0; i < trail; i++) {

            final int appNum = in.nextInt();
            final int server = in.nextInt();
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();
            for (int j = 0; j < server; j++) {
                all.add(in.oneLineToInt(" "));
            }
            res = launch(appNum, all);
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static ArrayList<Integer> launch(int appNum, ArrayList<ArrayList<Integer>> all) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Server> tmp = new ArrayList<>(all.size());
        for (ArrayList<Integer> integers : all) {
            final Server e = new Server(integers.get(0));
            for (int i = 1; i < integers.size(); i++) {
                e.add(integers.get(i));
            }
            tmp.add(e);
        }
        Collections.sort(tmp);
        HashSet<Integer> count = new HashSet<>();
        for (Server server : tmp) {
            Integer miniumApp = server.getMiniumApp();
            while (count.contains(miniumApp)) {
                miniumApp = server.getMiniumApp();
            }
            res.add(miniumApp);
            count.addAll(server.tmp);
            if (count.size() == appNum) {
                break;
            }
        }
        return res;
    }

    private static class Server implements Comparable<Server>{

        private final Integer dis;
        private TreeSet<Integer> apps = new TreeSet<>();
        private TreeSet<Integer> tmp = new TreeSet<>();

        public Server(Integer dis) {
            this.dis = dis;
        }

        public void add(Integer app) {
            apps.add(app);
            tmp.add(app);
        }

        public Integer getMiniumApp() {
            final Integer first = apps.first();
            apps.remove(first);
            return first;
        }

        @Override
        public int compareTo(Server o) {
            return Integer.compare(dis, o.dis);
        }
    }
}
