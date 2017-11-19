/**
  * Created by zzt on 11/19/17.
  *
  * <h3></h3>
  */
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics}
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.{Encoders, SparkSession}

object Classify {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Classify").master("local").getOrCreate()

    val trainDF = spark.read.option("header", "false").csv("hdfs://master:9000/ml/train/*.csv")

    val parsedData = trainDF.map(
      r => {
        //view_times, per_pay, score, comment_cnt, shop_level, pay_times, avg
        val feature = Array(r.getString(1).toDouble, r.getString(3).toDouble, r.getString(4).toDouble, r.getString(5).toDouble, r.getString(6).toDouble, r.getString(7).toDouble, r.getString(8).toDouble)
        LabeledPoint(r.getString(0).toDouble, Vectors.dense(feature))
      }
    )(Encoders.kryo[LabeledPoint])

    /*特征标准化优化*/
    val vectors = parsedData.map(x => x.features)(Encoders.kryo[org.apache.spark.mllib.linalg.Vector])
    //每列的方差
    val scaler = new StandardScaler(withMean = true, withStd = true).fit(vectors.rdd)
    //标准化
    val scaled_data = parsedData.map(point => LabeledPoint(point.label, scaler.transform(point.features)))(Encoders.kryo[LabeledPoint]).randomSplit(Array(0.7,0.3),11L)

    val data_train=scaled_data(0)
    val data_test=scaled_data(1)

    val model_log=new LogisticRegressionWithLBFGS().setNumClasses(2).run(data_train.rdd)

    /*在使用模型做预测时，如何知道预测到底好不好呢？换句话说，应该知道怎么评估模型性能。
    通常在二分类中使用的评估方法包括：预测正确率和错误率、准确率和召回率、准确率  召回率
    曲线下方的面积、 ROC 曲线、 ROC 曲线下的面积和 F-Measure*/
    val metrics=Seq(model_log).map{
      model =>
        val socoreAndLabels=data_test.map {
          point => (model.predict(point.features), point.label)
        }(Encoders.kryo[(Double, Double)])
        val metrics=new BinaryClassificationMetrics(socoreAndLabels.rdd)
        (model.getClass.getSimpleName,metrics.areaUnderPR(),metrics.areaUnderROC())
    }
    val allMetrics = metrics
    allMetrics.foreach{ case (m, pr, roc) =>
      println(f"$m, Area under PR: ${pr * 100.0}%2.4f%%, Area under ROC: ${roc * 100.0}%2.4f%%")
    }
  }
}
