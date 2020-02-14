package com.akka.tutorial.model

import com.fasterxml.jackson.annotation.JsonProperty

case class News(@JsonProperty("category") category:String,
                @JsonProperty("headline") headline:String,
                @JsonProperty("authors") authors:String,
                @JsonProperty("link") link:String,
                @JsonProperty("short_description") short_description:String,
                @JsonProperty("date") date:String) {

}
