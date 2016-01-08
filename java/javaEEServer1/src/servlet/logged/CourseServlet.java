package servlet.logged;

import entity.Course;
import entity.Score;
import filter.LogInFilter;
import filter.VisitCounter;
import listener.LoginCounter;
import remote.JNDIFactory;
import service.CourseService;
import service.ScoreService;
import servlet.InternalError;
import servlet.LoginServlet;

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
import java.sql.SQLException;
import java.util.ArrayList;
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
            "        <th>course name</th>\n" +
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
            // direct browse this page
            HttpSession session = req.getSession(false);
            sid = (int) session.getAttribute(LoginServlet.SID);
        } else {
            // redirected by log in page
            sid = Integer.valueOf(idStr);
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf8");
        PrintWriter out = resp.getWriter();

        // starting writing
        out.print(HEAD);

        CourseService courseService
                = (CourseService) JNDIFactory.getResource("java:/TryEJB//CourseEJB!service.CourseService");
        ScoreService scoreService
                = (ScoreService) JNDIFactory.getResource("java;/TryEJB//ScoreEJB!service.ScoreService");
        if (courseService == null || scoreService == null) {
            InternalError.forward(req, resp, InternalError.INTERNAL_ERROR);
            return;
        }
        ArrayList<Score> scores = scoreService.studentCourses(sid);
        for (Score score : scores) {
            out.print("<tr><th>");
            out.print(score.getStudent().getSid() + "</th>\n" +
                    "        <th>");
            int cid = score.getCourse().getCid();
            out.print(cid + "</th>\n" +
                    "        <th>");
            Course course = courseService.getCourse(cid);
            out.print(course.getCname() + "</th>\n");
            boolean submit = score.isSubmit();
            if (!submit) {
                out.print("<th style=\"color: red;\">未提交");
            } else {
                out.print("<th>已提交");
            }
            out.print("</th>\n" +
                    "</tr>");
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
