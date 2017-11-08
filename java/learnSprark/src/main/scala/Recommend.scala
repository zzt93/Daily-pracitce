import GraphXDemo.readPayGraph
import org.apache.spark.graphx.{Graph, VertexId, VertexRDD}
import org.apache.spark.sql.SparkSession

import scala.collection.mutable
import scala.language.postfixOps

/**
  * Created by zzt on 11/3/17.
  *
  * <h3></h3>
  */

object Recommend {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()
    val viewDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/user_view.txt")
    import spark.implicits._
    // find all shops user(12796832) visited
    //    viewDF.select("_c0", "_c1").distinct().groupBy("_c0").count().filter(r => r.getAs[Long](1) > 1).sort($"count".desc).show()
    val shops = viewDF.filter($"_c0" === 21668938).select("_c1").distinct().map(r => -r.getAs[String](0).toLong).collect()
    println(shops.length)
    //     find shops that has the most buying behavior
    val payGraph = readPayGraph
    //    recommend1(payGraph, shops)
    recommend2(payGraph, shops)
  }

  private def recommend1(payGraph: Graph[(String, String, String, String, String, String, String, String, String), VertexId], shopSet: Array[Long]): Unit = {
    val sub = payGraph.subgraph(vpred = (id, _) => id > 0 || shopSet.contains(id))
    val inDegrees: (VertexId, _) = sub.inDegrees.max()
    println(inDegrees._1, inDegrees._2)
  }

  private def recommend2(payGraph: Graph[(String, String, String, String, String, String, String, String, String), VertexId], shops: Array[Long]): Unit = {
    // get shop types from shop ids
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()
    val shop = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")
    import spark.implicits._
    //    shop.select("_c0", "_c1").distinct().groupBy("_c0").count().filter(r => r.getAs[Long](1) > 1).sort($"count".desc).show()
    val shopList = shops.map(l=> (-l).toString)
    val shopsTypes = shop.filter($"_c0".isin(shopList: _*) && ($"_c9" isNotNull)).select("_c9").distinct().map(r => r.getAs[String](0)).collect().toSet

    val sub = payGraph.subgraph(vpred = (id, attr) => id > 0 || (attr._9 != null && !attr._9.isEmpty && shopsTypes.contains(attr._9)))
    val inDegrees: (VertexId, _) = sub.inDegrees.max()
    println(inDegrees._1, inDegrees._2)
  }

}