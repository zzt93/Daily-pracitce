package dao;

import javaBean.Bean;
import javaBean.CourseBean;

import javax.naming.NamingException;
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

    private final PreparedStatement select;

    public CourseDAO() throws SQLException, NamingException {
        super();
        this.select = connection.prepareStatement("select * from course where cid = ?");
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
    protected <E extends Bean> void selectRes(ResultSet resultSet, ArrayList<E> res) throws SQLException {
        int cid = resultSet.getInt(1);
        String cname = resultSet.getString(2);
        res.add(new CourseBean(cid, cname));
    }
}
