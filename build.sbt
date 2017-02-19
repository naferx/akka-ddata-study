import Dependencies._


organization := "com.github.naferx"
scalaVersion := "2.11.8"
version      := "0.1.0-SNAPSHOT"

name := "akka-ddata-study"
libraryDependencies ++= Seq(scalaTest % Test, ddata)

