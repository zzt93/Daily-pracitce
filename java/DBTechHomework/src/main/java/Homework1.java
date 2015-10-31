import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Homework1 {

    private String dbName;

    /**
     * ex1.1
     */
    public Connection createTables() throws SQLException, IOException, ClassNotFoundException {
        Connection mysqlConnection = getMysqlConnection();
        String createCate = "create table if not exists " + dbName +
                ".Category " +
                "(cid integer NOT NULL AUTO_INCREMENT, " +
                "c_name varchar(40) NOT NULL, " +
                "PRIMARY KEY (cid))";
        createTable(mysqlConnection, createCate);

        String createSubCate = "create table if not exists " + dbName +
                ".SubCate " +
                "(cid integer NOT NULL, " +
                "sub_cid integer, " +
                "PRIMARY KEY (cid, sub_cid), " +
                "FOREIGN KEY (cid) REFERENCES " +
                dbName + ".Category (cid))";
        createTable(mysqlConnection, createSubCate);

        String createBook = "create table if not exists " + dbName +
                ".Book " +
                "(bid integer NOT NULL, " +
                "title varchar(40) NOT NULL, " +
                "author varchar(40) NOT NULL, " +
                "info varchar(40) NOT NULL, " +
                "cid integer NOT NULL, " +
                "PRIMARY KEY (bid), " +
                "FOREIGN KEY (cid) REFERENCES " +
                dbName + ".Category (cid))";
        createTable(mysqlConnection, createBook);

        String createBorrow = "create table if not exists " + dbName +
                ".Borrow " +
                "(sid integer NOT NULL, " +
                "bid integer NOT NULL, " +
                "b_t date NOT NULL, " +
                "r_t date , " +
                "PRIMARY KEY (bid, b_t), " +
                "FOREIGN KEY (bid) REFERENCES " +
                dbName + ".Book (bid))";
        createTable(mysqlConnection, createBorrow);

        //TODO release the connection after create?
        return mysqlConnection;
    }

    private void createTable(Connection mysqlConnection, String createCate) throws SQLException {
        Statement preparedStatement = null;
        try {
            preparedStatement = mysqlConnection.createStatement();
            preparedStatement.executeUpdate(createCate);
        } catch (SQLException e) {
            DBUtility.printSQLException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    /**
     * ex1.2
     *
     * @param con The target connection
     */
    public void initCategoryData(Connection con) throws SQLException, IOException {
        Statement stmt = null;
        BufferedReader bufferedReader;
        try {
            bufferedReader = FileUtility.bufferedReader("./data/ex1/category.txt");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        String str;

        try {
            stmt = con.createStatement();
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
                stmt.executeUpdate(
                        "insert into " + dbName +
                                ".Category " +
                                "values('" + str +
                                "')"
                );
            }

        } catch (SQLException e) {
            DBUtility.printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            bufferedReader.close();
        }
    }

    /**
     * ex1.3
     */
    public void insertNewCategoryData() {

    }

    /**
     * ex1.4
     */
    public void initBookAndBorrowInfoData() {

    }

    /**
     * ex1.5
     */
    public void getBorrowData() {

    }


    /**
     * get mysql connection
     *
     * @return Mysql connection
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Connection getMysqlConnection() throws SQLException, IOException, ClassNotFoundException {
        // get items from resources/config.properties
        Properties properties = new Properties();
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("config.properties").getPath();
        properties.load(new FileInputStream(new File(path)));

        String mysqlIP = properties.getProperty("mysql.server.ip");
        String mysqlPort = properties.getProperty("mysql.server.port");
        dbName = properties.getProperty("mysql.db.ex1");
        String loginName = properties.getProperty("mysql.login.name");
        String password = properties.getProperty("mysql.login.password");
        String url = "jdbc:mysql://" + mysqlIP + ":" + mysqlPort + "/" + dbName
                + "?" + "user=" + loginName + "&password=" + password
                + "&useUnicode=true&characterEncoding=UTF8";

        // load class driver
        /**
         In previous versions of JDBC, to obtain a connection,
         you first had to initialize your JDBC driver by calling the method Class.forName.
         This methods required an object of type java.sql.Driver.
         Each JDBC driver contains one or more classes that implements
         the interface java.sql.Driver.
         The drivers for Java DB are org.apache.derby.jdbc.EmbeddedDriver
         and org.apache.derby.jdbc.ClientDriver,
         and the one for MySQL Connector/J is com.mysql.jdbc.Driver.
         See the documentation of your DBMS driver to
         obtain the name of the class that implements the interface java.sql.Driver.

         Any JDBC 4.0 drivers that are found in your class path
         are automatically loaded. (However, you must manually
         load any drivers prior to JDBC 4.0 with the method Class.forName.)
         */
        Class.forName("com.mysql.jdbc.Driver");

        // return connection
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        Homework1 homework1 = new Homework1();
        try {
            Connection con = homework1.createTables();
            homework1.initCategoryData(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
