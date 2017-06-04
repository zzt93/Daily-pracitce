package competition.leetcode.w35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 6/4/17.
 * <p>
 * <h3></h3>
 */
public class DupInFileSystem {

    private static class MyFile {
        private String name;
        private String content;

        public MyFile(String dir, String s) {
            int i = s.indexOf("(");
            name = dir + "/" + s.substring(0, i);
            content = s.substring(i + 1, s.length() - 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyFile myFile = (MyFile) o;

            return content.equals(myFile.content);
        }

        @Override
        public int hashCode() {
            return content.hashCode();
        }
    }

    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<MyFile, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] split = path.split(" ");
            String dir = split[0];
            for (int i = 1; i < split.length; i++) {
                MyFile myFile = new MyFile(dir, split[i]);
                List<String> orDefault = map.getOrDefault(myFile, new ArrayList<>());
                orDefault.add(myFile.name);
                map.put(myFile, orDefault);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> strings : map.values()) {
            if (strings.size() > 1) {
                res.add(strings);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DupInFileSystem system = new DupInFileSystem();
        System.out.println(system.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}));
        System.out.println(system.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"}));
    }
}
