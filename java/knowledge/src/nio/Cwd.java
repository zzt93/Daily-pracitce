package nio;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by zzt on 9/11/15.
 * <p>
 * Description: using cwd to decide where to find file
 */
public class Cwd {
    public void readFile(String name) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(name), "utf-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        reader.lines().map(s -> "\"" + s + "\"").forEachOrdered(System.out::println);
    }

    public boolean checkPath(String name) {
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        File file = new File(name);
        return file.exists();
    }

    public static void main(String[] args) {
        final Cwd cwd = new Cwd();
        final String name = "./src/nio/Cwd.java";
        if (cwd.checkPath(name)) {
            cwd.readFile(name);
        } else {
            System.out.println(name + "not exist");
        }
    }
}
