package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by zzt on 12/22/15.
 * <p>
 * Usage:
 */
@WebFilter(
        urlPatterns = "/html/visitor.html"
)
public class VisitCounter implements Filter{
    public static final String VISITED = "Visited";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        addVisited(request);
        chain.doFilter(request, response);
    }

    private synchronized void addVisited(ServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Integer visited = (Integer) servletContext.getAttribute(VISITED);
        if (visited == null) {
            visited = 1;
        } else {
            visited++;
        }
        servletContext.setAttribute(VISITED, visited);
    }

    @Override
    public void destroy() {

    }
}
