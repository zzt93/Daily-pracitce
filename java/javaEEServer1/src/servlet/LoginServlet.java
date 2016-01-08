package servlet;


import servlet.logged.CourseServlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zzt on 12/9/15.
 * <p>
 * Usage:
 */
//    session id
//    dispatcher.include

/**
 * The request path is further composed of the following elements: Context path: A concatenation of a forward slash (/)
 * with the context root of the servlet’s web application. Servlet path: The path section that corresponds to the
 * component alias that activated this request. This path starts with a forward slash (/). Path info: The part of the
 * request path that is not part of the context path or the servlet path.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    public static final String SID = "sid";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("sid");
        int sid = Integer.valueOf(idStr);
        String pw = request.getParameter("password");

        Connection connection;
        try {
            connection = MyDataSource.getConnection();
        } catch (NamingException | SQLException e) {
            InternalError.forward(request, response, InternalError.INTERNAL_ERROR);
            e.printStackTrace();
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT password FROM student WHERE sid = " + sid);
            if (resultSet.next()) {
                String dbPW = resultSet.getString("password");
                if (!dbPW.equals(pw)) {
                    goToLogIn(request, response);
                    return;
                }
            } else {
                goToLogIn(request, response);
                return;
            }
        } catch (SQLException e) {
            InternalError.forward(request, response, InternalError.INTERNAL_ERROR);
            e.printStackTrace();
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        SessionManagement.setSession(request, sid);

        // use the path relative to this servlet
        InternalError.forward(request, response, CourseServlet.COURSE);
    }


    /**
     * The difference between redirect and forward 1. redirect is client redirect, while forward is server side redirect
     * 2. so, redirect will show the of url of target location, while forward show the url of original page with the new
     * resources 3. so redirect is slow and forward is fast
     *
     * @param resp the response send back to client
     *
     * @throws ServletException
     * @throws IOException
     */
    private void goToLogIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<head><title>" + "No such user or wrong password</title></head><body>");
        out.println("<h2>" + "No such user or wrong password</h2></body>");
        out.println("Click <a href=" + req.getContextPath() + resp.encodeURL("/html/login.html") + ">here</a>");
        out.println(" to retry log in. ");
    }

}
