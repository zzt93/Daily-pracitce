package remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by zzt on 12/31/15.
 * <p>
 * Usage:
 */
public class JNDIFactory {

    private static Context context;

    static {
        final Hashtable properties = new Hashtable();
        /**
         * Colon-separated list of package prefixes of URL context factories.
         * The prefix consists of the URL scheme id and a suffix to construct the class name,
         * as follows:
         prefix.schemeId.schemeIdURLContextFactory
         * Note: 1. A schema specifies the types of objects that a directory may contain.
         *       2. other properties are stored in the jboss-ejb-client-properties
         *
         * This is necessary because we should let the JNDI API know
         * what handles the ejb: namespace that we use in our JNDI names for lookup.
         * The "org.jboss.ejb.client.naming" has a URLContextFactory implementation
         * which will be used by the JNDI APIs to parse and return an object
         * for ejb: namespace lookups.
         */
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            /**
             * In the JNDI, all naming and directory operations are performed relative to a context.
             * There are no absolute roots.
             * Therefore the JNDI defines an InitialContext,
             * which provides a starting point for naming and directory operations.
             */
            context = new InitialContext(properties);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Object getResource(String jndiName) {
        try {
            /**
             * In the JNDI, all naming and directory operations are performed relative to a context.
             * There are no absolute roots.
             * Therefore the JNDI defines an InitialContext,
             * which provides a starting point for naming and directory operations.
             */
            return context.lookup(jndiName);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
