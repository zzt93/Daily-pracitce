package servlet;

import filter.LogInFilter;

import javax.naming.NamingException;
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
            "</table>\n" +
            "</body>\n" +
            "</html>";

    public static final String COURSE = LogInFilter.LOGGED_DIR + "course";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showCourse(req, resp);
    }

    private void showCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("sid");
        int sid;
        if (idStr == null) {
            HttpSession session = req.getSession(false);
            sid = (int) session.getAttribute(LoggedServlet.SID);
        } else {
            sid = Integer.valueOf(idStr);
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf8");
        PrintWriter out = resp.getWriter();
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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showCourse(req, resp);
    }
}
