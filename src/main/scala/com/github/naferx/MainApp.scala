package com.github.naferx

import akka.actor.ActorSystem
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

object MainApp extends App {

  implicit val system = ActorSystem("DData")
  val config = ConfigFactory.load()

  System.getProperty("cluster.role") match {

    case "seed" =>


    case "frontend" =>
      val interface = config.getString("server.interface")
      val port = config.getInt("server.port")

      // Starting the server
      HttpServer.startServer(interface, port, ServerSettings(system), system)
    // system.terminate()

  }



}
