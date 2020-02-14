package com.akka.tutorial.actors

import akka.actor.ActorLogging
import com.akka.tutorial.model.{News, Result}


class CategoryWorker(category: String) extends AppActor with ActorLogging{
  private val newsList = scala.collection.mutable.MutableList[News]()

  override def receive: Receive = {
    case n: News => {
      newsList += n
    }
    case r: Result => {
      println(s"${category} count ${newsList.size}")
    }
    case _ =>
      println("error")
  }
}

object CategoryWorker {
  def apply(category: String): CategoryWorker = {
    new CategoryWorker(category)
  }
}
