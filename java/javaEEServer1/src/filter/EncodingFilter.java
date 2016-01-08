package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by zzt on 12/16/15.
 * <p>
 * Usage:
 * <p>
 */
@WebFilter(
        urlPatterns = "/*",
        filterName = "EncodingFilter",
        initParams = {
                @WebInitParam(name = "token.name", value = "version"),
                @WebInitParam(name = "token.value", value = "3.0")
        }
)
public class EncodingFilter implements Filter {
    private String replToken;
    private String replValue;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        replToken = fConfig.getInitParameter("token.name");
        replValue = fConfig.getInitParameter("token.value");
        if (null == replToken) {
            throw new ServletException("TokenReplacementFilter named " + fConfig.getFilterName() + " missing token.name init parameter.");
        }
        if (null == replValue) {
            throw new ServletException("TokenReplacementFilter named " + fConfig.getFilterName() + " missing token.value init parameter.");
        }
    }

    /**
     * When will this method be invoked? - ask for a resource; not invoked when returning resource to client
     *
     * @param request  User request
     * @param response Real response or a stand-in response
     * @param chain    Filters use the FilterChain to invoke the next filter in the chain, or if the calling filter is
     *                 the last filter in the chain, to invoke the resource at the end of the chain.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        /*
         * This method has no effect if it is called after getWriter has been called or after the response has been committed.
         * Note that the character encoding cannot be communicated via HTTP headers if the servlet does not specify a content
         * type; however, it is still used to encode text written via the servlet response's writer
         */
        response.setContentType("text/html;charset=utf-8");

        // have to call this to make request go to servlet
        // so if it is return from this method, response is already set
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
