package dao;

import servlet.MyDataSource;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zzt on 12/23/15.
 * <p>
 * Usage:
 */
public abstract class DAO {

    private Connection connection;
    public DAO() throws SQLException, NamingException {
        connection = MyDataSource.getConnection();
    }

    /**
     *
     * @return The sql statement which is a select statement using a unique/foreign key
     */
    public abstract String getKeySelect();
    public abstract <T, E> ArrayList<E> setKey(T t);
    public abstract String getUpdate();
    public abstract String getInsert();

    public <E> ArrayList<E> keySelect() throws SQLException {
        ArrayList<E> res = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getKeySelect());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
//            res.add(resultSet.get);
        }
        return res;
    }
}
