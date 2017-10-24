import org.apache.spark.sql.SparkSession


/**
  * Created by zzt on 10/23/17.
  *
  * <h3></h3>
  */

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/usr/local/spark/README.md" // Should be some file on your system
    val spark = SparkSession.builder.appName("Simple Application").master("local").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}

