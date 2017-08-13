package com.github.naferx

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directive
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object HttpServer extends App {

  implicit val system = ActorSystem("HttpServer")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val loggingRequest: Directive[Unit] =
    extractRequestContext.flatMap { ctx =>
      extractClientIP.flatMap { client =>
          mapRequest { request =>
            ctx.log.info(s"${ctx.request.method.name} ${ctx.request.uri.path} ${client.toOption.map(_.getHostAddress).getOrElse("unknown")}")
            request
          }
      }
    }


  val routes = loggingRequest {
    path("services") {
      get {
        complete("selecting resource")
      } ~
      post {
        complete("creating resource")
      }
    }
  }

  val binding =
    Http().bindAndHandle(routes, "0.0.0.0", 8089)
  println(
    s"Server online at http://0.0.0.0:8089/\nPress RETURN to stop...")
  StdIn.readLine()
  binding.flatMap(_.unbind()).onComplete(_ => system.terminate())
}
