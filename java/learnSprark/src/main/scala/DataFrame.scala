import org.apache.spark.sql.SparkSession

/**
  * Created by zzt on 10/24/17.
  *
  * <h3></h3>
  */
val spark = SparkSession.builder.appName("Spark SQL basic example").master("local").getOrCreate()

// For implicit conversions like converting RDDs to DataFrames
import spark.implicits._

val df = spark.read.format("csv").option("header", "false").load("~/mis/NJU/研一/cloud/IJCAI17_data/dataset/shop_info.txt")

// Displays the content of the DataFrame to stdout
df.show()
df.printSchema()
// root
// |-- age: long (nullable = true)
// |-- name: string (nullable = true)

// Select only the "name" column
df.select("name").show()
