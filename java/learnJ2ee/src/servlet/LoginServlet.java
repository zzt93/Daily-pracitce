package servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("sid");
        int sid = Integer.valueOf(idStr);
        String pw = request.getParameter("spw");
        boolean canlog = false;

        //        RequestDispatcher view = request.getRequestDispatcher(url);
        //        view.forward(request, response);
    }
}
