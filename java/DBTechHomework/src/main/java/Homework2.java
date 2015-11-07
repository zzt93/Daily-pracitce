import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.BiConsumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


public class Homework2 {

    public static final int LINE_LIMIT = 1000;
    /**
     * The MongoClient instance actually represents a pool of connections to the database; you will only need one
     * instance of class MongoClient even with multiple threads.
     */
    private static MongoClient mongoClient = null;

    private String dbName;
    private String url;
    private String mogoDbName;

    public Homework2() throws IOException {
        initFromProperty();
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

//        if (mongoClient == null) {
//            String mongoIP = properties.getProperty("mongo.server.ip");
//            int mongoPort = Integer.parseInt(properties.getProperty("mongo.server.port"));
//            //The database doesn’t have to exist -if it doesn’t, MongoDB will create it for you.
//            mogoDbName = properties.getProperty("mongo.db.ex2");
//
//            //The MongoClient class is designed to be thread safe and shared among threads.
//            // Typically you create only 1 instance for a given database cluster
//            // and use it across your application.
//            mongoClient = new MongoClient(mongoIP, mongoPort);
//        }
    }


    public void mysqlCreateAndInsert() throws Exception {
        mysqlCreate();
        //        mysqlInsert();
        String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
        //        mySqlImport("Teacher", cwd + "/data/ex2/teacher.txt");
        //        mySqlImport("Course", cwd + "/data/ex2/course.txt");
        mySqlImport("Score", cwd + "/data/ex2/score.txt");
    }

    private void mySqlImport(String table, String inputFile) throws Exception {
        Connection mysqlConnection = getMysqlConnection();
        Statement statement = mysqlConnection.createStatement();
        // add local so it will
        long start = System.currentTimeMillis();
        // LOAD DATA INFILE queries can be very complex
        // and achieve very good data mapping without ever having to run any other code,
        // as long as the imported data is going into a single table.
        // TODO it seems that I can't load data by 'local'?
        String importStr = "LOAD DATA LOCAL INFILE '" + inputFile +
                "' INTO TABLE hw2." + table +
                " IGNORE 1 LINES";
        statement.execute(importStr);
        System.out.println(System.currentTimeMillis() - start);
        mysqlConnection.close();
    }

    //    private void mysqlInsert() throws Exception {
    //        long start = System.currentTimeMillis();
    //        // init student data
    //        batchInsert("./data/ex2/student.txt", "insert into " + dbName + ".Student values(?, ?, ?)",
    //                (statement, split) -> {
    //                    try {
    //                        statement.setInt(1, Integer.parseInt(split[0]));
    //                        statement.setString(2, split[1].trim());
    //                        // skip the department
    //                        statement.setString(3, split[3].trim());
    //                    } catch (SQLException e) {
    //                        e.printStackTrace();
    //                    }
    //                });
    //
    //        // init teacher data
    //        batchInsert("./data/ex2/teacher.txt", "insert into " + dbName + ".Teacher values(?, ?, ?)",
    //                (statement, split) -> {
    //                    try {
    //                        statement.setInt(1, Integer.parseInt(split[0]));
    //                        statement.setString(2, split[1].trim());
    //                        statement.setString(3, split[2].trim());
    //                    } catch (SQLException e) {
    //                        e.printStackTrace();
    //                    }
    //                });
    //
    //        // init teacher data
    //        batchInsert("./data/ex2/course.txt", "insert into " + dbName + ".Course values(?, ?, ?, ?)",
    //                (statement, split) -> {
    //                    try {
    //                        statement.setInt(1, Integer.parseInt(split[0]));
    //                        statement.setString(2, split[1].trim());
    //                        statement.setInt(3, Integer.parseInt(split[2]));
    //                        statement.setString(4, split[3].trim());
    //                    } catch (SQLException e) {
    //                        e.printStackTrace();
    //                    }
    //                });
    //
    //        // init score data
    //        batchInsert("./data/ex2/score.txt", "insert into " + dbName + ".Score values(?, ?, ?)",
    //                (statement, split) -> {
    //                    try {
    //                        statement.setInt(1, Integer.parseInt(split[0]));
    //                        statement.setInt(2, Integer.parseInt(split[1]));
    //                        statement.setInt(3, Integer.parseInt(split[2]));
    //                    } catch (SQLException e) {
    //                        e.printStackTrace();
    //                    }
    //                });
    //
    //        System.out.println(System.currentTimeMillis() - start);
    //
    //    }

    //    private void batchInsert(String path, String insertStr, BiConsumer<PreparedStatement, String[]> setParameter) throws Exception {
    //        BufferedReader bufferedReader;
    //        try {
    //            bufferedReader = FileUtility.bufferedReader(path);
    //        } catch (FileNotFoundException | UnsupportedEncodingException e) {
    //            e.printStackTrace();
    //            return;
    //        }
    //        //skip the first line
    //        bufferedReader.readLine();
    //        Connection con = getMysqlConnection();
    //        con.setAutoCommit(false);
    //
    //        PreparedStatement stmt = con.prepareStatement(insertStr);
    //
    //        String next;
    //        int i = 0;
    //        while ((next = bufferedReader.readLine()) != null) {
    //            //System.out.println(next);
    //            String[] split = next.split("\t");
    //            setParameter.accept(stmt, split);
    //            stmt.addBatch();
    //
    //            i++;
    //            if (i % LINE_LIMIT == 0) {
    //                i = 0;
    //                stmt.executeBatch();
    //            }
    //        }
    //        // will batch be too large -- will out of memory for large data...
    //        stmt.executeBatch();
    //        con.commit();
    //
    //        con.close();
    //    }

    private void mysqlCreate() throws Exception {
        Connection connection = getMysqlConnection();

        String createStudent = "create table if not exists " + dbName +
                ".Student " +
                "(sid integer NOT NULL, " +
                "name varchar(40) NOT NULL, " +
                "region varchar(40) NOT NULL, " +
                "PRIMARY KEY (sid))" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        Homework1.createTable(connection, createStudent);

        String createTeacher = "create table if not exists " + dbName +
                ".Teacher " +
                "(tid integer NOT NULL, " +
                "tname varchar(40) NOT NULL, " +
                "dep varchar(40) NOT NULL, " +
                "PRIMARY KEY (tid))" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        Homework1.createTable(connection, createTeacher);

        String createCourse = "create table if not exists " + dbName +
                ".Course " +
                "(cid integer NOT NULL, " +
                "title varchar(40) NOT NULL, " +
                "tid integer NOT NULL, " +
                "semester varchar(160) NOT NULL, " +
                "PRIMARY KEY (cid), " +
                "FOREIGN KEY (tid) REFERENCES " +
                dbName + ".Teacher (tid))" +
                "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        Homework1.createTable(connection, createCourse);

        String createScore = "create table if not exists " + dbName +
                ".Score " +
                "(sid integer NOT NULL, " +
                "cid integer NOT NULL, " +
                "grade integer NOT NULL) ";
//                "PRIMARY KEY (sid, cid)) " +
//                "FOREIGN KEY (sid) REFERENCES " +
//                dbName + ".Student (sid), " +
//                "FOREIGN KEY (cid) REFERENCES " +
//                dbName + ".Course (cid))";
        Homework1.createTable(connection, createScore);

        connection.close();
    }


    public void mysqlQuery() throws Exception {
        long start = System.currentTimeMillis();
        Connection mysqlConnection = getMysqlConnection();
        Statement stmt = mysqlConnection.createStatement();
        String query = "SELECT Score.sid, Score.grade " +
                "from " + dbName + ".Score " +
                "where Score.sid = 131250072 " +
                "and Score.cid in ( " +
                "  SELECT cid " +
                "  FROM Course " +
                "  WHERE tid = ( " +
                "    SELECT tid " +
                "    FROM Teacher " +
                "    WHERE tname = '刘嘉'))";
        ResultSet rs = stmt.executeQuery(query);
        StringBuilder stringBuilder = new StringBuilder();
        while (rs.next()) {
            // don't forget this is 1-based
            stringBuilder.append(rs.getInt(1)).append(" ").append(rs.getInt(2)).append("\n");
        }

        System.out.println(System.currentTimeMillis() - start);

        System.out.println(stringBuilder.toString());

        mysqlConnection.close();
    }

    public void mongoCreateAndInsert() throws Exception {
        //        mongoTest();

        long start = System.currentTimeMillis();
        // init student data
        mongoBulkInsert("Student", "./data/ex2/student.txt",
                (list, split) ->
                        list.add(new InsertOneModel<>(
                                new Document("sid", Integer.parseInt(split[0]))
                                        .append("name", split[1])
                                        .append("region", split[3])
                        ))
        );
        System.out.println(System.currentTimeMillis() - start);

        // init teacher data
        mongoBulkInsert("Teacher", "./data/ex2/teacher.txt",
                (list, split) ->
                        list.add(new InsertOneModel<>(
                                new Document("tid", Integer.parseInt(split[0]))
                                        .append("tname", split[1])
                                        .append("dep", split[2])
                        ))
        );
        System.out.println(System.currentTimeMillis() - start);

        // init course data
        mongoBulkInsert("Course", "./data/ex2/course.txt",
                (list, split) ->
                        list.add(new InsertOneModel<>(
                                new Document("cid", Integer.parseInt(split[0]))
                                        .append("title", split[1])
                                        .append("tid", Integer.parseInt(split[2]))
                                        .append("semester", split[3])
                        ))
        );
        System.out.println(System.currentTimeMillis() - start);

        // init score data
        mongoBulkInsert("Score", "./data/ex2/score.txt",
                (list, split) ->
                        list.add(new InsertOneModel<>(
                                new Document("sid", Integer.parseInt(split[0]))
                                        .append("cid", Integer.parseInt(split[1]))
                                        .append("grade", Integer.parseInt(split[2]))
                        ))
        );

        System.out.println(System.currentTimeMillis() - start);
    }

    //    private void mongoTest() throws Exception {
    //        MongoCollection<Document> collection = getMongoDB().getCollection("Stu");
    //        Document doc = new Document("sid", 1)
    //                .append("name", "曾泽堂")
    //                .append("region", "1去");
    //        collection.insertOne(doc);
    //    }


    private void mongoBulkInsert(String name, String path,
                                 BiConsumer<List<InsertOneModel<Document>>, String[]> setParameter) throws Exception {
        BufferedReader bufferedReader;
        try {
            bufferedReader = FileUtility.bufferedReader(path);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        //skip the first line
        bufferedReader.readLine();
        MongoCollection<Document> collection = getMongoDB().getCollection(name);

        String next;
        int i = 0;
        List<InsertOneModel<Document>> list = new ArrayList<>(LINE_LIMIT + 1);
        while ((next = bufferedReader.readLine()) != null) {
            //System.out.println(next);
            String[] split = next.split("\t");
            setParameter.accept(list, split);

            i++;
            if (i % LINE_LIMIT == 0) {
                collection.bulkWrite(list, new BulkWriteOptions().ordered(false));
                i = 0;
                list.clear();
            }
        }
        // will batch be too large -- will out of memory for large data...
        if (!list.isEmpty()) {
            collection.bulkWrite(list, new BulkWriteOptions().ordered(false));
        }

    }

    public void mongoQuery() throws Exception {
        MongoDatabase mongoDB = getMongoDB();

        long start = System.currentTimeMillis();

        MongoCollection<Document> collection = mongoDB.getCollection("Teacher");
        Document teacher = collection.find(eq("tname", "刘嘉")).first();
        int tid = (int) teacher.get("tid");
        MongoCollection<Document> course = mongoDB.getCollection("Course");
        ArrayList<Integer> cidList = new ArrayList<>();
        try (MongoCursor<Document> cids = course.find(eq("tid", tid)).iterator()) {
            while (cids.hasNext()) {
                //                System.out.println();
                cidList.add((Integer) cids.next().get("cid"));
            }
        }
        MongoCollection<Document> score = mongoDB.getCollection("Score");
        MongoCursor<Document> res =
                score.find(and(eq("sid", 131250072), in("cid", cidList)))
                        .projection(fields(include("sid", "grade"), excludeId()))
                        .iterator();
        StringBuilder stringBuilder = new StringBuilder();
        res.forEachRemaining(doc -> stringBuilder.append(doc.get("sid")).append("\t").append(doc.get("grade")).append("\n"));

        System.out.println(System.currentTimeMillis() - start);

        System.out.println(stringBuilder.toString());
    }

    /**
     * get mysql connection
     *
     * @return Just return connection
     *
     * @throws Exception
     */
    public Connection getMysqlConnection() throws Exception {
        return DriverManager.getConnection(url);
    }


    public MongoDatabase getMongoDB() throws Exception {
        return mongoClient.getDatabase(mogoDbName);
    }

    public static void main(String[] args) {
        try {
            Homework2 homework2 = new Homework2();
//            homework2.mysqlCreateAndInsert();
            homework2.mysqlQuery();
//            homework2.mongoCreateAndInsert();
//            homework2.mongoQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
