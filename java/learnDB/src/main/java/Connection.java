import java.sql.*;
import java.util.Properties;

/**
 * Created by zzt on 10/29/15.
 * <p>
 * Usage:
 */
public class Connection {
    public static final String MYSQL = "mysql";
    private String userName = "zzt";
    private String password = "1993";
    private String dbms;
    private String serverName;
    private String portNumber;
    private String database;

    public Connection(String dbms, String serverName, String portNumber, String database) {
        this.dbms = dbms;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.database = database;
    }

    public Connection() {
        this(MYSQL, "localhost", "3306", "hw");
    }

    public java.sql.Connection getConnection() throws SQLException {

        java.sql.Connection conn = null;
        Properties connectionProps = new Properties();
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("config.properties").getPath();
        properties.load(new FileInputStream(new File(path)));

//        connectionProps.put("user", this.userName);
//        connectionProps.put("password", this.password);

        String loginName = properties.getProperty("mysql.login.name");
        String password = properties.getProperty("mysql.login.password");

        if (this.dbms.equals(MYSQL)) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://" +
                            this.serverName +
                            ":" + this.portNumber + "/" + this.database,
                    connectionProps);
        }
//        else if (this.dbms.equals("derby")) {
//            conn = DriverManager.getConnection(
//                    "jdbc:" + this.dbms + ":" +
//                            this.dbName +
//                            ";create=true",
//                    connectionProps);
//        }
        System.out.println("Connected to database");
        return conn;
    }
}

