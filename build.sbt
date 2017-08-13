import com.typesafe.sbt.SbtMultiJvm.multiJvmSettings
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

val akkaVersion = "2.5.4"
val akkaHttpVersion = "10.0.9"

val `akka-ddata-study` = project
  .in(file("."))
  .settings(multiJvmSettings: _*)
  .settings(
    organization := "com.github.naferx",
    scalaVersion := "2.11.11",
    scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    javaOptions in run ++= Seq("-Xms128m", "-Xmx1024m"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-distributed-data" % akkaVersion,
      "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
      "ch.qos.logback"            %     "logback-classic"            %    "1.1.7",

      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "org.iq80.leveldb"            % "leveldb"          % "0.7",
      "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8",

      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test),
    fork in run := true,
    // disable parallel tests
    parallelExecution in Test := false
  )
  .configs (MultiJvm)

addCommandAlias("seed1", "run -DROLES.0=seed -DPORT=2551")
addCommandAlias("seed2", "run -DROLES.0=seed -DPORT=2551")
addCommandAlias("frontend1", "run -DROLES.0=frontend -DPORT=8081")
addCommandAlias("frontend2", "run -DROLES.0=frontend -DPORT=8082")