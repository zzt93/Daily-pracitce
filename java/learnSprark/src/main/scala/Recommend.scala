import GraphXDemo.readPayGraph
import org.apache.spark.graphx.{VertexId, VertexRDD}
import org.apache.spark.sql.SparkSession

import scala.collection.mutable

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
    val shopSet = shops.toSet
    //     find shops that has the most buying behavior
    val payGraph = readPayGraph
    val sub = payGraph.subgraph(vpred = (id, _) => id > 0 || shopSet.contains(id))
//    sub.vertices.foreach(println)
    val inDegrees: (VertexId, _) = sub.inDegrees.max()
    println(inDegrees._1, inDegrees._2)
  }
}