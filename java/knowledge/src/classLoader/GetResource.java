package classLoader;

import java.net.URL;

/**
 * Created by zzt on 3/11/17.
 * <p>
 * <h3></h3>
 */
public class GetResource {

    public static void main(String[] args) {
        classGet();
        classLoaderGet();
    }

    private static void classLoaderGet() {
        final URL file = GetResource.class.getClassLoader().getResource("/file");
        final URL file2 = GetResource.class.getClassLoader().getResource("file");
    }

    private static void classGet() {
        final URL file = GetResource.class.getResource("file");
        final URL file2 = GetResource.class.getResource("/file");
    }
}
