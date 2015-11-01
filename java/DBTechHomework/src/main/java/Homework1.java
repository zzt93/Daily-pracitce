import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Homework1 {

    public static final int LINE_MAX_CHAR = 100;
    private String dbName;
    private String url;

    public Homework1() throws IOException {
        initFromProperty();
    }

    private void initFromProperty() throws IOException {
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
        url = "jdbc:mysql://" + mysqlIP + ":" + mysqlPort + "/" + dbName
                + "?" + "user=" + loginName + "&password=" + password
                + "&useUnicode=true&characterEncoding=UTF8&rewriteBatchedStatements=true";
    }

    /**
     * http://stackoverflow.com/questions/935098/database-structure-for-tree-data-structure
     * <p>
     * ex1.1
     */
    public void createTables() throws SQLException, IOException, ClassNotFoundException {
        Connection mysqlConnection = getMysqlConnection();

        String createCate = "create table if not exists " + dbName +
                ".Category " +
                "(cid integer NOT NULL AUTO_INCREMENT, " +
                "c_name varchar(40) NOT NULL, " +
                "sup_cid integer, " +
                "PRIMARY KEY (cid))" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        createTable(mysqlConnection, createCate);

        String createBook = "create table if not exists " + dbName +
                ".Book " +
                "(bid integer NOT NULL, " +
                "title varchar(160) NOT NULL, " +
                "author varchar(160) NOT NULL, " +
                "info varchar(160) NOT NULL, " +
                "cid integer NOT NULL, " +
                "PRIMARY KEY (bid), " +
                "FOREIGN KEY (cid) REFERENCES " +
                dbName + ".Category (cid))" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        createTable(mysqlConnection, createBook);

        String createBorrow = "create table if not exists " + dbName +
                ".Borrow " +
                "(boid integer NOT NULL AUTO_INCREMENT, " +
                "sid integer NOT NULL, " +
                "bid integer NOT NULL, " +
                "b_t date NOT NULL, " +
                "r_t date , " +
                "PRIMARY KEY (boid), " +
                "FOREIGN KEY (bid) REFERENCES " +
                dbName + ".Book (bid))";
        createTable(mysqlConnection, createBorrow);

        mysqlConnection.close();
    }

    public static void createTable(Connection mysqlConnection, String createCate) throws SQLException {
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
     */
    public void initCategoryData() throws SQLException, IOException {
        PreparedStatement stmt = null;
        BufferedReader bufferedReader;
        try {
            bufferedReader = FileUtility.bufferedReader("./data/ex1/category.txt");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        int sup_id = -1;
        String next;
        Connection con = null;
        try {
            con = getMysqlConnection();
            stmt = con.prepareStatement("insert into " + dbName + ".Category (c_name, sup_cid) " +
                    "values(?, ?)");

            while ((next = bufferedReader.readLine()) != null) {
                System.out.println(next);
                recursiveAdd(next, sup_id, stmt, bufferedReader);
            }

        } catch (SQLException e) {
            DBUtility.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            bufferedReader.close();
            if (con != null) {
                con.close();
            }
        }
    }

    private void recursiveAdd(String now, int supId,
                              PreparedStatement stmt, BufferedReader bufferedReader) throws IOException, SQLException {
        // set it visited
        stmt.setString(1, cateStrHandle(now));
        if (supId == -1) {
            stmt.setNull(2, Types.INTEGER);
        } else {
            stmt.setInt(2, supId);
        }
        stmt.executeUpdate();

        bufferedReader.mark(LINE_MAX_CHAR);
        // check whether it has a subcategory
        String next = bufferedReader.readLine();
        if (next == null) {
            return;
        }
        if (isSub(next, now)) {
            do {
                recursiveAdd(next, getCidByName(stmt, now), stmt, bufferedReader);
                next = bufferedReader.readLine();
            } while (isSub(next, now));
        } else {
            bufferedReader.reset();
        }
    }

    private String cateStrHandle(String now) {
        return now.replaceAll("\\s|:", "");
    }


    private int getCidByName(Statement stmt, String str) throws SQLException {
        String query = "SELECT Category.cid " +
                "from " + dbName + ".Category " +
                "where Category.c_name = '" + cateStrHandle(str) + "'";
        ResultSet rs = stmt.executeQuery(query);
        assert rs.getFetchSize() == 1;
        if (rs.next()) {
            return rs.getInt("Category.cid");
        }
        throw new RuntimeException("not find this category");
    }

    private boolean isSub(String sub, String sup) {
        return countTab(sub) > countTab(sup);
    }

    private int countTab(String s) {
        return s.length() - s.replace("\t", "").length();
    }

    /**
     * ex1.3
     */
    public void insertNewCategoryData() throws SQLException, IOException, ClassNotFoundException {
        Connection mysqlConnection = getMysqlConnection();
        Statement statement = mysqlConnection.createStatement();
        int supId = getCidByName(statement, "自动化技术、计算机技术");
        String inserts = new StringBuilder()
                .append("insert into ")
                .append(dbName).append(".Category (c_name, sup_cid) ")
                .append("values('自动化基础理论', ").append(supId).append("), ")
                .append("('自动化技术及设备', ").append(supId).append("), ")
                .append("('计算技术、计算机技术', ").append(supId).append("), ")
                .append("('射流技术(流控技术)', ").append(supId).append("), ")
                .append("('远动技术', ").append(supId).append("), ")
                .append("('遥感技术', ").append(supId).append(")")
                .toString();
        statement.execute(inserts);
        mysqlConnection.close();
    }

    /**
     * ex1.4
     */
    public void initBookAndBorrowInfoData() throws IOException, SQLException {
        PreparedStatement stmt = null;
        BufferedReader bufferedReader = null;


        String next;
        Connection con = null;
        try {
            long start = System.currentTimeMillis();
            // prepare connection
            con = getMysqlConnection();
            con.setAutoCommit(false);

            // init book data
            try {
                bufferedReader = FileUtility.bufferedReader("./data/ex1/books.txt");
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
                return;
            }
            //skip the first line
            bufferedReader.readLine();
            stmt = con.prepareStatement("insert into " + dbName + ".Book " +
                    "values(?, ?, ?, ?, ?)");

            while ((next = bufferedReader.readLine()) != null) {
                //                System.out.println(next);
                String[] split = next.split("\t");
                stmt.setInt(1, Integer.parseInt(split[0]));
                stmt.setString(2, split[1].trim());
                stmt.setString(3, split[2].trim());
                stmt.setString(4, split[3].trim());
                // can use this stmt? -- work properly
                stmt.setInt(5, getCidByName(stmt, cateStrHandle(split[4])));
                stmt.addBatch();
            }
            // will batch be too large? -- work properly
            stmt.executeBatch();
            con.commit();

            // init borrow data
            try {
                bufferedReader = FileUtility.bufferedReader("./data/ex1/borrow.txt");
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
                return;
            }
            bufferedReader.readLine();
            stmt = con.prepareStatement("insert into " + dbName + ".Borrow (sid, bid, b_t, r_t)" +
                    "values(?, ?, ?, ?)");

            while ((next = bufferedReader.readLine()) != null) {
                //                System.out.println(next);
                String[] split = next.split("\t");
                stmt.setInt(1, Integer.parseInt(split[0]));
                stmt.setInt(2, Integer.parseInt(split[1]));
                stmt.setDate(3, Date.valueOf(split[2]));
                stmt.setDate(4, Date.valueOf(split[3]));
                stmt.addBatch();
            }
            stmt.executeBatch();
            con.commit();

            System.out.println(System.currentTimeMillis() - start);

        } catch (SQLException e) {
            DBUtility.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            bufferedReader.close();
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * ex1.5
     */
    public void getBorrowData() {
        try {
            Connection mysqlConnection = getMysqlConnection();
            long start = System.currentTimeMillis();
            PreparedStatement preparedStatement =
                    mysqlConnection.prepareStatement(
                            "SELECT b.b_t, b.r_t " +
                                    "FROM Borrow b " +
                                    "WHERE b.sid = '131250072' " +
                                    "and bid in (SELECT b2.bid " +
                                    "FROM Book b2 " +
                                    "WHERE b2.cid in (" +
                                    "select Category.cid from Category WHERE Category.sup_cid = " +
                                    getCidByName(mysqlConnection.createStatement(), "自动化技术、计算机技术") + "));"
                    );
            preparedStatement.executeQuery();
            System.out.println((System.currentTimeMillis() - start));
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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
    public Connection getMysqlConnection() throws SQLException, IOException, ClassNotFoundException {
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
        Homework1 homework1;
        try {
            homework1 = new Homework1();
            //                        homework1.createTables();
            //            homework1.initCategoryData();
            //            homework1.insertNewCategoryData();
            homework1.initBookAndBorrowInfoData();
            //            homework1.getBorrowData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
