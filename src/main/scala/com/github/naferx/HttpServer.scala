package com.github.naferx

object HttpServer extends App {


  val routes =
      loggingRequest {
        statusRoute ~ transcriptionRoute
      }
}
