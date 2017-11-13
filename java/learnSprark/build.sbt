name := "learnSpark"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.2.0"
// https://mvnrepository.com/artifact/org.apache.spark/spark-mllib_2.11
libraryDependencies += "org.apache.spark" % "spark-mllib_2.11" % "2.2.0" % "provided"



libraryDependencies += "org.graphstream" % "gs-core" % "1.2"
// https://mvnrepository.com/artifact/org.graphstream/gs-ui
libraryDependencies += "org.graphstream" % "gs-ui" % "1.2"

// https://mvnrepository.com/artifact/org.scalanlp/breeze_2.10
libraryDependencies += "org.scalanlp" % "breeze_2.11" % "0.12"
// https://mvnrepository.com/artifact/org.scalanlp/breeze-viz_2.11
libraryDependencies += "org.scalanlp" % "breeze-viz_2.11" % "0.12"

// https://mvnrepository.com/artifact/org.jfree/jcommon
libraryDependencies += "org.jfree" % "jcommon" % "1.0.24"

// https://mvnrepository.com/artifact/org.jfree/jfreechart
libraryDependencies += "org.jfree" % "jfreechart" % "1.0.19"

