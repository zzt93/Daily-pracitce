/**
  * Created by zzt on 10/26/17.
  *
  * <h3></h3>
  */

import java.io.PrintWriter

import org.apache.spark.graphx.{Edge, Graph, GraphLoader, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Encoder, Row, SparkSession}

object GraphXTest {

  case class VertexInfo(attr: Long)

  def main(args: Array[String]) {
    val graph = readGraph

    //    vertices.filter { case (id, st:java.lang.String) => id == 149}.foreach(println)
    //    vertices2.filter { case (id, st:String) => id == 149}.foreach(println)
    //    edges.filter(ed => ed.srcId == 149).foreach(println)
    val sub = graph.subgraph(vpred = (v, attr) => (attr.length > 1) || v < 500)
    //    sub.vertices.foreach(v => printf("%s ", v))
    //    println()
    //    sub.edges.foreach(v => printf("%s ", v))
    val gexf = toGexf(sub)
    new PrintWriter("graph.gexf") {
      write(gexf)
      close()
    }
  }

  private def readGraph: Graph[String, VertexId] = {
    val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

    val payDF: DataFrame = spark.read.option("header", "false").csv("hdfs://master:9000/count2/part-00000-830d5a53-8c50-4474-a816-5e6ca92ca0fb-c000.csv")
    val userDF = spark.read.option("header", "false").csv("hdfs://master:9000/user/part-00000-caa1ceb6-ff84-4f44-acd8-49914b0dca7a-c000.csv")
    val shopDF = spark.read.option("header", "false").csv("hdfs://master:9000/shop/*.txt")

    implicit val edgeEncoder: Encoder[Edge[VertexId]] = org.apache.spark.sql.Encoders.kryo[Edge[VertexId]]
    implicit val vertexEncoder: Encoder[(VertexId, String)] = org.apache.spark.sql.Encoders.kryo[(VertexId, String)]

    val edges = payDF.map(row =>
      Edge(row.get(0).asInstanceOf[String].toLong, -row.get(1).asInstanceOf[String].toLong, row.get(2).asInstanceOf[String].toLong)
    )(edgeEncoder).rdd

    val vertices = userDF.map(row =>
      (row.get(0).asInstanceOf[String].toLong, "")
    )(vertexEncoder).rdd
    val vertices2 = shopDF.map(row =>
      (-row.get(0).asInstanceOf[String].toLong, row.get(1).asInstanceOf[String])
    )(vertexEncoder).rdd
    (edges, vertices, vertices2)

    Graph(vertices.union(vertices2), edges)
  }

  private def toGexf[VD, ED](g: Graph[VD, ED]): String = {
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">\n" +
      "  <graph mode=\"static\" defaultedgetype=\"directed\">\n" +
      "    <nodes>\n" +
      g.vertices.map(v => "      <node id=\"" + v._1 + "\" label=\"" +
        v._2 + "\" />\n").collect.mkString +
      "    </nodes>\n" +
      "    <edges>\n" +
      g.edges.map(e => "      <edge source=\"" + e.srcId +
        "\" target=\"" + e.dstId + "\" label=\"" + e.attr +
        "\" />\n").collect.mkString +
      "    </edges>\n" +
      "  </graph>\n" +
      "</gexf>"
  }
}
