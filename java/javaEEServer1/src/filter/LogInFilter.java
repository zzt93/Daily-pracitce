package filter;

import servlet.SessionManagement;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by zzt on 12/17/15.
 * <p>
 * Usage: Check the log in state before request reach the servlet
 */
@WebFilter(
        urlPatterns = LogInFilter.LOGGED
)
public class LogInFilter implements Filter {

    public static final String LOGGED_DIR = "/logged/";
    public static final String LOGGED = LOGGED_DIR + "*";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!SessionManagement.checkSession(request, response)) {
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
