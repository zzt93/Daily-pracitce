import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * Created by zzt on 10/23/17.
 *
 * <h3></h3>
 */
public class Count {

  public static void main(String[] args) {
    String logFile = "/usr/local/spark/README.md"; // Should be some file on your system
    SparkSession spark = SparkSession.builder().appName("Simple Application").master("local").getOrCreate();
    Dataset<String> logData = spark.read().textFile(logFile).cache();

    long numAs = logData.filter((FilterFunction<String>) line -> line.contains("a")).count();
    long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

    spark.stop();
  }
}
