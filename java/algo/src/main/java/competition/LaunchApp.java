package competition;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

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
        ArrayList<ServerTest> res;
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

    private static ArrayList<ServerTest> launch(int appNum, ArrayList<ArrayList<Integer>> all) {
        ArrayList<ServerTest> res = new ArrayList<>();
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
            Integer minimumApp = server.getMinimumApp();
            while (count.contains(minimumApp)) {
                minimumApp = server.getMinimumApp();
            }
            if (minimumApp == null) {
                continue;
            }
            res.add(new ServerTest(server.dis, minimumApp));
            count.addAll(server.backUp);
            if (count.size() == appNum) {
                break;
            }
        }
        return res;
    }

    private static class Server implements Comparable<Server> {

        private final Integer dis;
        private PriorityQueue<Integer> apps = new PriorityQueue<>();
        private ArrayList<Integer> backUp = new ArrayList<>();

        public Server(Integer dis) {
            this.dis = dis;
        }

        public void add(Integer app) {
            apps.add(app);
            backUp.add(app);
        }

        public Integer getMinimumApp() {
            return apps.poll();
        }

        @Override
        public int compareTo(Server o) {
            return Integer.compare(dis, o.dis);
        }
    }

    private static class ServerTest {
        private final int dis;
        private final int app;

        public ServerTest(Integer dis, Integer minimumApp) {
            this.dis = dis;
            this.app = minimumApp;
        }

        @Override
        public String toString() {
            return "ServerTest{" +
                    "dis=" + dis +
                    ", app=" + app +
                    '}';
        }
    }
}
