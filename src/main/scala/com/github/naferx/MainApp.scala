package com.github.naferx

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object MainApp extends App {

  implicit val system = ActorSystem("DData")
  val config = ConfigFactory.load()

  val interface = config.getString("server.interface")
  val port = config.getInt("server.port")

  println("ENV ->" + port)

}
