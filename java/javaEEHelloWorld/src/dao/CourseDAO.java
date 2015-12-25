package dao;

import javaBean.CourseBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class CourseDAO extends DAO{

    private PreparedStatement select;

    public CourseDAO() throws SQLException, NamingException {
        super();
        initStatement();
    }

    private void initStatement() throws SQLException {
        this.select = connection.prepareStatement("select * from course where cid = ?");
    }

    public CourseDAO(Connection connection) throws SQLException {
        super(connection);
        initStatement();
    }

    @Override
    public <T> PreparedStatement getKeySelect(T t) throws SQLException {
        select.setObject(1, t);
        return select;
    }


    @Override
    public String getUpdate() {
        return null;
    }

    @Override
    public String getInsert() {
        return null;
    }

    @Override
    protected void selectRes(ResultSet resultSet, ArrayList res) throws SQLException {
        int cid = resultSet.getInt(1);
        String cname = resultSet.getString(2);
        res.add(new CourseBean(cid, cname));
    }

    public static void main(String[] args) throws SQLException, NamingException {
        ArrayList<CourseBean> keySelect = new CourseDAO().keySelect(2);
        System.out.println(keySelect);
    }
}
