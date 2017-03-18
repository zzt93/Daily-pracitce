package classLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;

/**
 * Created by zzt on 3/18/17.
 * <p>
 * <h3></h3>
 */
public class ClassNotFound extends ClassLoader {


    private URL base = Paths.get("/lib/java", "").toUri().toURL();
    private java.security.AccessControlContext acc = AccessController.getContext();
    private ProtectionDomain codesource;

    public ClassNotFound() throws MalformedURLException {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] b = AccessController.doPrivileged(
                    (PrivilegedExceptionAction<byte[]>) () -> {
                        try {
                            URL finalURL = new URL(base, name);

                            // Make sure the codebase won't be modified
                            if (base.getProtocol().equals(finalURL.getProtocol()) &&
                                    base.getHost().equals(finalURL.getHost()) &&
                                    base.getPort() == finalURL.getPort()) {
                                return getBytes(finalURL);
                            } else {
                                return null;
                            }
                        } catch (Exception e) {
                            return null;
                        }
                    }, acc);

            if (b != null) {
                return defineClass(name, b, 0, b.length, codesource);
            } else {
                throw new ClassNotFoundException(name);
            }
        } catch (PrivilegedActionException e) {
            throw new ClassNotFoundException(name, e.getException());
        }
    }

    private byte[] getBytes(URL finalURL) {
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        final ClassNotFound classNotFound = new ClassNotFound();
        classNotFound.loadClass("NotFound.class");
    }
}
