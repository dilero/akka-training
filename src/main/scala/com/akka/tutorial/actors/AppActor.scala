package com.akka.tutorial.actors

import akka.actor.Actor

import scala.concurrent.ExecutionContextExecutor

trait AppActor extends Actor {

  implicit val executionContext: ExecutionContextExecutor = context.dispatcher

}
