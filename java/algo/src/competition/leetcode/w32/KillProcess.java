package competition.leetcode.w32;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by zzt on 5/14/17.
 * <p>
 * <h3></h3>
 */
public class KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> children = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            Integer parent = ppid.get(i);
            List<Integer> list;
            if (children.containsKey(parent)) {
                list = children.get(parent);
            } else {
                list = new ArrayList<>();
                children.put(parent, list);
            }
            list.add(pid.get(i));
        }

        List<Integer> res = new ArrayList<>();
        res.add(kill);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            List<Integer> node = children.get(queue.poll());
            if (node == null) {
                continue;
            }
            res.addAll(node);
            queue.addAll(node);
        }
        return res;
    }

    public static void main(String[] args) {
        KillProcess killProcess = new KillProcess();
        System.out.println(killProcess.killProcess(
                Lists.newArrayList(1, 3, 10, 5), Lists.newArrayList(3, 0, 5, 3), 5));
        System.out.println(killProcess.killProcess(
                Lists.newArrayList(1, 3, 10, 5), Lists.newArrayList(3, 0, 5, 3), 3));
        System.out.println(killProcess.killProcess(
                Lists.newArrayList(1, 3, 10, 5), Lists.newArrayList(3, 0, 5, 3), 1));
        System.out.println(killProcess.killProcess(
                Lists.newArrayList(1, 3, 10, 5, 11), Lists.newArrayList(3, 0, 5, 3, 5), 5));
        System.out.println(killProcess.killProcess(
                Lists.newArrayList(3), Lists.newArrayList(0), 3));
    }
}
