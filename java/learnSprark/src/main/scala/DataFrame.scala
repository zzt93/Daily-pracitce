import org.apache.spark.sql.{Encoders, Row, SparkSession}
/**
  * Created by zzt on 10/24/17.
  *
  * <h3></h3>
  */
object DataFrame {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

    val df = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/user_pay.txt")
//    val df = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")
    // Displays the content of the DataFrame to stdout
    //    df.show()
//    df.printSchema()
    //    df.select("_c1").show()
    //    df.groupBy("_c1", "_c8").count().show()
//    df.groupBy("_c0", "_c1").count().write.csv("hdfs://master:9000/count2")
//    df.select("_c0").distinct().write.csv("hdfs://master:9000/user/")
    import spark.implicits._
    df.filter($"_c0" < 150).show()
//    df.filter($"age" > 21).show()
  }

}
