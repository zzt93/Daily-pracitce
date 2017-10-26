/**
  * Created by zzt on 10/26/17.
  *
  * <h3></h3>
  */
import org.apache.spark.graphx.{Edge, Graph, GraphLoader, VertexId}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object GraphXTest {
  //  Edge(row.get(0).asInstanceOf[Long], row.get(1).asInstanceOf[Long], row.get(2))
  case class VertexInfo(attr: Long)
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

    val payDF:DataFrame = spark.read.option("header", "false").csv("hdfs://master:9000/count2/*.csv")
    val userDF = spark.read.option("header", "false").csv("hdfs://master:9000/user/*.csv")
    val shopDF = spark.read.option("header", "false").csv("/home/zzt/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")

    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Edge[Long]]
    implicit val mapEncoder2 = org.apache.spark.sql.Encoders.kryo[(Long, String)]

    val edges = payDF.map(row =>
      Edge(row.get(0).asInstanceOf[String].toLong, -row.get(1).asInstanceOf[String].toLong, row.get(2).asInstanceOf[String].toLong)
    )(mapEncoder).rdd

    val vertices = userDF.map(row =>
      (row.get(0).asInstanceOf[String].toLong, "")
    )(mapEncoder2).rdd
    val vertices2 = shopDF.map(row =>
      (-row.get(0).asInstanceOf[String].toLong, row.get(1).asInstanceOf[String])
    )(mapEncoder2).rdd

//    vertices.filter { case (id, st:java.lang.String) => id == 149}.foreach(println)
//    vertices2.filter { case (id, st:String) => id == 149}.foreach(println)
//    edges.filter(ed => ed.srcId == 149).foreach(println)
    val graph = Graph(vertices.union(vertices2), edges, "")
    val value = graph.subgraph(vpred = (v, attr) => (attr.length > 1 ) || v > 22528068)
    value.vertices.foreach(println)
    value.edges.foreach(println)
  }
}
