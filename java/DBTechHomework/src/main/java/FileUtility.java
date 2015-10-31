import java.io.*;

/**
 * Created by zzt on 10/31/15.
 * <p>
 * Usage:
 */
public class FileUtility {

    public static BufferedReader bufferedReader(String path) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
    }
}
