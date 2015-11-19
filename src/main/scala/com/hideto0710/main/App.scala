package com.hideto0710.main

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.typesafe.scalalogging.Logger
import org.apache.commons.lang3.time.StopWatch
import org.slf4j.LoggerFactory

object App extends App {
  implicit val stopWatch = new StopWatch()
  val logger = Logger(LoggerFactory.getLogger("App"))

  val k = "keyword"
  val f = "filter"
  val logId = "logId"

  val now = ZonedDateTime.now()
  val dates = TchBench((0 to 34).toList.map(i =>
    now.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
  ), (s: String) => logger.info(s))
    .start(s"""es query started: k -> $k, f -> $f, logId -> $logId""")
    .finish((s) => s"""es query finished: duration -> $s, k -> $k, f -> $f, logId -> $logId""").execute
}