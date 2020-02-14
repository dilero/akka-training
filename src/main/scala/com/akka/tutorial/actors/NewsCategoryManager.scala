package com.akka.tutorial.actors

import akka.actor.{ActorLogging, ActorRef, Props}
import com.akka.tutorial.model.{News, Result}

class NewsCategoryManager(categoryList: List[String])
  extends AppActor with ActorLogging {
  private val workers: Map[String, ActorRef] = (categoryList zip categoryList.map(spawnCategoryWorker)).toMap

  override def receive: Receive = {
    case message@News(category, _, _, _, _, _) =>
      workers.get(category.replaceAll("\\s", "")) match {
        case Some(worker) =>
          println(s"Forwarding ${message.getClass.getSimpleName} to $worker")
          worker forward message
        case None =>
          println(s"Couldn't find actor for category $category")
      }

    case message@Result(category) =>
      workers.get(category.replaceAll("\\s", "")) match {
        case Some(worker) =>
          println(s"Forwarding ${message.getClass.getSimpleName} to $worker")
          worker forward message
        case None =>
          println(s"Couldn't find actor for category $category")
      }
  }

  private def spawnCategoryWorker(category: String): ActorRef = {
    context.actorOf(Props(CategoryWorker(category)), category)
  }
}

object NewsCategoryManager {
  def props(categoryList: List[String]): Props = Props(new NewsCategoryManager(categoryList))
}
