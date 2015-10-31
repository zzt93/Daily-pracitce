import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zzt on 10/29/15.
 *
 * Usage:
 */


public class UpdateCar {

    public static void UpdateCarNum(int carNo, int empNo)
            throws SQLException {

        java.sql.Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:default:connection");

            pstmt = con.prepareStatement(
                    "UPDATE EMPLOYEES " +
                            "SET CAR_NUMBER = ? " +
                            "WHERE EMPLOYEE_NUMBER = ?");

            pstmt.setInt(1, carNo);
            pstmt.setInt(2, empNo);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getCause());
        } finally {

            if (pstmt != null) pstmt.close();
        }
    }
}
