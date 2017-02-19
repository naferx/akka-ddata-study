import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val ddata = "com.typesafe.akka" %% "akka-distributed-data-experimental" % "2.4.17"
}
