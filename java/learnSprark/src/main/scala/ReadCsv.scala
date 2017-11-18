import org.apache.spark.sql.{DataFrame, Encoders, Row, SparkSession}

/**
  * Created by zzt on 10/24/17.
  *
  * <h3></h3>
  */
object ReadCsv {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

    val payDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/user_pay.txt")
//    val payDF = spark.read.option("header", "false").csv("hdfs://master:9000/count2/*.csv")
    val viewDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/user_view.txt")

//    payDF.groupBy("_c0", "_c1").count().write.csv("hdfs://master:9000/count2")
//    payDF.select("_c0").distinct().write.csv("hdfs://master:9000/user/")
    import spark.implicits._
    payDF.filter($"_c2" > "2016-06-22" && $"_c2" < "2016-07-21").sort("_c2").write.csv("hdfs://master:9000/ml/pay/06")
    payDF.filter($"_c2" > "2016-07-29" && $"_c2" < "2016-08-28").sort("_c2").write.csv("hdfs://master:9000/ml/pay/07")
    payDF.filter($"_c2" > "2016-08-22" && $"_c2" < "2016-09-07").sort("_c2").write.csv("hdfs://master:9000/ml/pay/08")
    viewDF.filter($"_c2" > "2016-07-22" && $"_c2" < "2016-08-21").sort("_c2").write.csv("hdfs://master:9000/ml/view/07")
    viewDF.filter($"_c2" > "2016-08-22" && $"_c2" < "2016-08-31").sort("_c2").write.csv("hdfs://master:9000/ml/view/08")
  }

  private def showShop(payDF: DataFrame, spark: SparkSession) = {
    import spark.implicits._
    val shopCount = payDF.select("_c1").distinct().count()
    println(shopCount)

    payDF.show()
    payDF.printSchema()
    payDF.select("_c1").show()
    payDF.groupBy("_c1", "_c8").count().show()
    payDF.filter($"_c0" < 150).show()
    payDF.filter($"age" > 21).show()
  }

  private def tryShop(spark: SparkSession) = {
    val shopDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")
    import spark.implicits._
    val set = Array(1, 2, 3).toList
    val shopsTypes = shopDF.filter($"_c0".isin(set: _*) && ($"_c9" isNotNull)).select("_c9").distinct().map(r => r.getAs[String](0)).collect()
    print(shopsTypes)
    shopDF.write.csv("hdfs://master:9000/shop/")
  }
}
