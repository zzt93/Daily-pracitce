import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by zzt on 11/2/15.
 * <p>
 * Usage:
 */
public class AutoAnalyze implements Job {

    private String url;
    private String dbName;

    public AutoAnalyze() throws Exception {
        initFromProperty();
        initTable();
    }

    private void initFromProperty() throws IOException {
        //get items from resources/config.properties
        Properties properties = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
        properties.load(new FileInputStream(new File(path)));

        String mysqlIP = properties.getProperty("mysql.server.ip");
        String mysqlPort = properties.getProperty("mysql.server.port");
        dbName = properties.getProperty("mysql.db.ex2");
        String loginName = properties.getProperty("mysql.login.name");
        String password = properties.getProperty("mysql.login.password");
        url = "jdbc:mysql://" + mysqlIP + ":" + mysqlPort + "/" + dbName + "?"
                + "user=" + loginName + "&password=" + password + "&useUnicode=true&characterEncoding=UTF8&rewriteBatchedStatements=true";
    }

    public Connection getMysqlConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    private void initTable() throws Exception {
        Connection connection = getMysqlConnection();

        String create = "create table if not exists " + dbName +
                ".Analysis " +
                "(tid integer NOT NULL, " +
                "cid integer NOT NULL, " +
                "avg integer NOT NULL, " +
                "region varchar(40) NOT NULL, " +
                "grade integer NOT NULL, " +
                "PRIMARY KEY (cid, region), " +
                "FOREIGN KEY (tid) REFERENCES " +
                dbName + ".Teacher (tid), " +
                "FOREIGN KEY (cid) REFERENCES " +
                dbName + ".Course (cid) )" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        Homework1.createTable(connection, create);

        String index = "alter table Score index(sid);";
        String index2 = "alter table Score index(cid);";


        updateSemesters();

        connection.close();
    }

    private void updateSemesters() throws Exception {
        Connection mysqlConnection = getMysqlConnection();

        String query = "SELECT DISTINCT c.semester " +
                "FROM Course c;";
        Statement statement = mysqlConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<String> allSeme = new ArrayList<>();
        while (resultSet.next()) {
            allSeme.add(resultSet.getString(1));
        }
        allSeme.forEach(this::analyzeASemester);

        mysqlConnection.close();
    }

    private void analyzeASemester(String semester) {
        long start = System.currentTimeMillis();
        Connection mysqlConnection = null;
        try {
            mysqlConnection = getMysqlConnection();
            Statement stmt = mysqlConnection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String query = "INSERT INTO Analysis (SELECT " +
                "                        t.tid, " +
                "                        s.cid, " +
                "                        avg(s.grade) avg, " +
                "                        s2.region, " +
                "                        s2.sid DIV 10000000 " +
                "                      FROM Score s " +
                "                        JOIN Student s2 ON s2.sid = s.sid " +
                "                        JOIN Course c ON s.cid = c.cid " +
                "                        JOIN Teacher t ON t.tid = c.tid " +
                "                      WHERE t.dep = '软件学院' AND c.semester = '" + semester +
                "' " +
                "                      GROUP BY t.tid, s.cid, s2.region, s2.sid DIV 10000000); ";

        System.out.println(System.currentTimeMillis() - start);

        try {
            mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void autoUpdate() throws Exception {
        final String first = "学年第";
        final String second = "学期";
        final String yearPre = "20";
        String split = "-";

        String res = yearPre;
        //2013-2014学年第1学期
        int year = java.util.Calendar.getInstance().get(Calendar.YEAR);
        int month = java.util.Calendar.getInstance().get(Calendar.MONTH);
        StringBuilder stringBuilder = new StringBuilder();
        if (month == 3) {
            res += (stringBuilder.append(year - 1).append(split).append(yearPre).append(year).append(first).append(2).append(second).toString());
        } else {
            assert month == 9;
            res += (stringBuilder.append(year).append(split).append(yearPre).append(year + 1).append(first).append(1).append(second).toString());
        }
        analyzeASemester(res);
    }


    public static void main(String[] args) throws Exception {
//        SchedulerFactory sf = new StdSchedulerFactory();
//        Scheduler sched = sf.getScheduler();
//        JobDetail job = newJob(AutoAnalyze.class)
//                .withIdentity("job1", "group1")
//                .build();
        AutoAnalyze autoAnalyze = new AutoAnalyze();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            autoUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
