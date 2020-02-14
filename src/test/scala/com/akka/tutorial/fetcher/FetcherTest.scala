package com.akka.tutorial.fetcher

import org.scalatest.{FlatSpec, Matchers}

class FetcherTest extends FlatSpec with Matchers {
  NewsParser.read()(0).authors shouldBe  "Melissa Jeltsen"


}
