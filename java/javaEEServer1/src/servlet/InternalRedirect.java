package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/11/15.
 * <p>
 * Usage:
 */

@WebServlet("/" + InternalRedirect.INTERNAL_ERROR)
public class InternalRedirect extends HttpServlet{


    public static final String INTERNAL_ERROR = "internalError";

    /**
     * forward(), sendRedirect(), or sendError() can't invoke more than once or it cause
     * java.lang.IllegalStateException: Cannot forward after response has been committed
     * @param request
     * @param response
     * @param servlet
     */
    public static void forward(HttpServletRequest request, HttpServletResponse response, String servlet) {
        /*
        If the path begins with a "/" it is interpreted as relative to the current context root.
         */
        RequestDispatcher dispatcher = request.getRequestDispatcher(servlet);
        if (dispatcher == null) {
            throw new RuntimeException("no such servlet: " + servlet);
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>" + "Server internal error!</title></head>" + "server internal error! Can't log in</h1></body></html>");
    }
}
