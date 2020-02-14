package com.akka.tutorial.actors

import akka.actor.ActorSystem
import com.akka.tutorial.fetcher.NewsParser
import com.akka.tutorial.model.Result
import org.scalatest.{FlatSpec, Matchers}

class ActorTest extends FlatSpec with Matchers {
  val allNews = NewsParser.read();
  val categories =allNews.map(n => n.category.replaceAll("\\s", "")).distinct.toList

  val system: ActorSystem = ActorSystem("categoryAkka")
  val categoryManager = system.actorOf(NewsCategoryManager.props(categories))

  allNews.foreach(n => categoryManager ! n)

  categoryManager ! Result("POLITICS")

}
