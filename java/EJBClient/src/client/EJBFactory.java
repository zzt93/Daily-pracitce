package client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by zzt on 12/31/15.
 * <p>
 * Usage:
 */
public class EJBFactory {

    public static Object getEJB(String jndiName) {
        final Hashtable properties = new Hashtable();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context;
        try {
            context = new InitialContext(properties);
            return context.lookup(jndiName);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
