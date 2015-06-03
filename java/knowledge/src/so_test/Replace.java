package so_test;

import java.io.File;

/**
 * Created by zzt on 5/30/15.
 * <p>
 * Description:
 */
public class Replace {
    public static void main(String[] args) {
        File file = new File("IntegerArray.txt");
        String path = file.getAbsolutePath();
//        String path = "int\\java\\a.out";
        String split = System.getProperty("file.separator");
        System.out.println(split);
        path = path.replace(split, "\\\\");
//        path = path.replaceAll("\\\\", "\\\\\\\\");
        System.out.println(path);
    }
}
