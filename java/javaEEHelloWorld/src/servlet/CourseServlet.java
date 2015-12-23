package servlet;

import filter.LogInFilter;
import filter.VisitCounter;
import listener.LoginCounter;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 12/10/15.
 * <p>
 * Usage:
 */

@WebServlet(CourseServlet.COURSE)
public class CourseServlet extends HttpServlet {

    public static final String HEAD = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Course</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table>\n" +
            "    <caption>Your homework status</caption>\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <th>sid</th>\n" +
            "        <th>cid</th>\n" +
            "        <th>submit status</th>\n" +
            "    </tr>";
    private static final String END = "</tbody>\n" +
            "</table>\n";

    public static final String COURSE = LogInFilter.LOGGED_DIR + "course";
    private static final AtomicInteger countOfInit = new AtomicInteger(0);
    public static final String COURSE_ID = "cid";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // it seems that only one instance will be created for a deployed app
        // so the instance field is not thread safe, for all request ask for a
        // single instance for response
        countOfInit.getAndIncrement();
        System.out.println(countOfInit);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showCourse(req, resp);
    }

    private void showCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("sid");
        int sid;
        if (idStr == null) {
            HttpSession session = req.getSession(false);
            sid = (int) session.getAttribute(LoginServlet.SID);
        } else {
            sid = Integer.valueOf(idStr);
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf8");
        PrintWriter out = resp.getWriter();

        // starting writing
        out.print(HEAD);
        Connection connection;
        try {
            connection = MyDataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            InternalError.forward(req, resp, InternalError.INTERNAL_ERROR);
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM score WHERE sid = " + sid);
            while (resultSet.next()) {
                out.print("<tr><th>");
                out.print(resultSet.getInt(1) + "</th>\n" +
                        "        <th>");
                out.print(resultSet.getInt(2) + "</th>\n");
                boolean submit = resultSet.getBoolean(3);
                if (!submit) {
                    out.print("<th  style=\"color: red;\">未提交");
                } else {
                    out.print("<th>已提交");
                }
                out.print("</th>\n" +
                        "</tr>");
            }
        } catch (SQLException e) {
            InternalError.forward(req, resp, InternalError.INTERNAL_ERROR);
            e.printStackTrace();
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            InternalError.forward(req, resp, InternalError.INTERNAL_ERROR);
            return;
        }
        out.print(END);

        // add count information
        ServletContext servletContext = req.getServletContext();
        Integer visited = (Integer) servletContext.getAttribute(VisitCounter.VISITED);
        if (visited == null) {
            visited = 0;
        }
        int logged = (int) servletContext.getAttribute(LoginCounter.LOGGED_NUM);
        out.print("<h3>All online:" + (logged + visited) + "</h3>");
        out.print("<h3>User:" + (logged) + "</h3>");
        out.print("<h3>Visitor:" + (visited) + "</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showCourse(req, resp);
    }
}
