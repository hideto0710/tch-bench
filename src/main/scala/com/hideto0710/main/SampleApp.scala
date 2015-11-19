package com.hideto0710.main

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.hideto0710.TchBench
import com.typesafe.scalalogging.Logger
import org.apache.commons.lang3.time.StopWatch
import org.slf4j.LoggerFactory

object SampleApp extends App {
  implicit val stopWatch = new StopWatch()
  val logger = Logger(LoggerFactory.getLogger("App"))
  val bench = TchBench((s: String) => logger.info(s))

  val datesBench = bench
    .start("bench started:")
    .finish((d) => s"""bench finished: duration -> $d""")

  val now = ZonedDateTime.now()
  val dates = datesBench.execute((0 to 34).toList.map(i =>
    now.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
  ))
}