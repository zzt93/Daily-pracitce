import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.mongodb.MongoClient;


public class Homework2 {
	public void mysqlCreateAndInsert(){
		
	}
	
	public void mysqlQuery(){
		
	}
	
	public void mongoCreateAndInsert(){
		
	}
	
	public void mongoQuery(){
		
	}
	
	/**
	 * get mysql connection
	 * @return
	 * @throws Exception
	 */
	public Connection getMysqlConnection() throws Exception{
		//get items from resources/config.properties
		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		properties.load(new FileInputStream(new File(path)));
		
		String mysqlIP = properties.getProperty("mysql.server.ip");
		String mysqlPort =  properties.getProperty("mysql.server.port");
		String dbName =  properties.getProperty("mysql.db.ex2");
		String loginName =  properties.getProperty("mysql.login.name");
		String password =  properties.getProperty("mysql.login.password");
		String url = "jdbc:mysql://"+mysqlIP+":"+mysqlPort+"/"+dbName+"?"
                + "user="+loginName+"&password="+password+"&useUnicode=true&characterEncoding=UTF8";
		
		//load class driver
		Class.forName("com.mysql.jdbc.Driver");
		
		//return connection
		Connection connection = DriverManager.getConnection(url);
		return connection;
	}
	
	
	public MongoClient getMongoClient() throws Exception{
		//get items from resources/config.properties
		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		properties.load(new FileInputStream(new File(path)));
		
		String mongoIP = properties.getProperty("mongo.server.ip");
		int mongoPort = Integer.parseInt(properties.getProperty("mongo.server.port"));
		String dbName = properties.getProperty("mongo.db.ex2");
		
		//return mongoClient
		MongoClient mongoClient = new MongoClient(mongoIP, mongoPort);
		return mongoClient;
	}
}
