package servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zzt on 12/11/15.
 * <p>
 * Usage:
 */
public class MyDataSource {


    public static Connection getConnection() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/studentDB");
        return ds.getConnection();
    }


    //    private boolean registerDB() throws NamingException {
    //        Context ctx = null;
    //        try {
    //            ctx = new InitialContext();
    //        } catch (NamingException e) {
    //            e.printStackTrace();
    //            return true;
    //        }
    //        DataSource ds = null;
    //        ds.setServerName("grinder");
    //        ds.setDatabaseName("student");
    //        ds.setDescription("Customer accounts database for billing");
    //        ctx.bind("jdbc/studentDB", ds);
    //        return false;
    //    }
}
