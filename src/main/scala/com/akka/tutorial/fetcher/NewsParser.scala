package com.akka.tutorial.fetcher

import java.io.InputStream

import com.akka.tutorial.model.News
import com.fasterxml.jackson.databind.ObjectMapper
import resource.managed

object NewsParser {
  val mapper = new ObjectMapper()

  def read(): Array[News] = {
    managed(getClass.getResourceAsStream("News_Category_Dataset_v2.json"))
      .acquireAndGet { inputStream =>
        val newsArray = parse(inputStream)
        newsArray
      }
  }

  def parse(inputStream: InputStream): Array[News] = {
    mapper.readValue[Array[News]](inputStream, classOf[Array[News]])
  }


}
