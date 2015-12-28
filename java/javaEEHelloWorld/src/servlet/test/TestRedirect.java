package servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/28/15.
 * <p>
 * Usage:
 */
@WebServlet(
        urlPatterns = TestRedirect.TEST_DIR +
                "TestRedirect"
)
public class TestRedirect extends HttpServlet {

    public static final String TEST_DIR = "/test/";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/html/login.html");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Search</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"../emptySearch.jsp\">\n" +
                "    <label> Search for: <br>\n" +
                "        <input type=\"text\" name=\"keywords\">\n" +
                "        <input type=\"submit\">\n" +
                "    </label>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }
}
