package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zzt on 12/17/15.
 * <p>
 * Usage:
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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.sendRedirect(req.getContextPath() + "/html/login.html");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
