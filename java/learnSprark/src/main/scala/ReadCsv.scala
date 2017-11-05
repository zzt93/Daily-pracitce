import org.apache.spark.sql.{Encoders, Row, SparkSession}
/**
  * Created by zzt on 10/24/17.
  *
  * <h3></h3>
  */
object ReadCsv {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

    val payDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/user_pay.txt")
//    val shopDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")
//    shopDF.write.csv("hdfs://master:9000/shop/")

//    val shopCount = payDF.select("_c1").distinct().count()
//    println(shopCount)

    // Displays the content of the DataFrame to stdout
    //    payDF.show()
//    payDF.printSchema()
    //    payDF.select("_c1").show()
        payDF.groupBy("_c1", "_c8").count().show()
//    payDF.groupBy("_c0", "_c1").count().write.csv("hdfs://master:9000/count2")
//    payDF.select("_c0").distinct().write.csv("hdfs://master:9000/user/")
//    import spark.implicits._
//    payDF.filter($"_c0" < 150).show()
//    payDF.filter($"age" > 21).show()
  }

}
