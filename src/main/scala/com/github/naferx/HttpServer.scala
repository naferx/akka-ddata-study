package com.github.naferx

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directive, HttpApp, Route}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn


object HttpServer extends HttpApp {

  private val loggingRequest: Directive[Unit] =
    extractRequestContext.flatMap { ctx =>
      extractClientIP.flatMap { client =>
          mapRequest { request =>
            ctx.log.info(s"${ctx.request.method.name} ${ctx.request.uri.path} ${client.toOption.map(_.getHostAddress).getOrElse("unknown")}")
            request
          }
      }
    }


  override val routes: Route = loggingRequest {
    path("services") {
      get {
        complete("selecting resource")
      } ~
      post {
        complete("creating resource")
      }
    }
  }

  /*
  private val binding =
    Http().bindAndHandle(routes, interface, port)
  println(
    s"Server online at http://${interface}:${port}/\nPress RETURN to stop...")
  StdIn.readLine()
  binding.flatMap(_.unbind()).onComplete(_ => system.terminate())
  */
}
