package servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by zzt on 12/10/15.
 * <p>
 * Usage:
 */

//@WebServlet("/") cause can't visit html file
@WebServlet(
        urlPatterns = "/helloWorld",
        smallIcon = "./logo.jpg"
)
public class HelloWorld extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>" + "Hello World!</title></head>" + "Hello this World!</h1></body></html>");
    }
}


