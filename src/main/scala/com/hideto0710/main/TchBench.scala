package com.hideto0710.main

import org.apache.commons.lang3.time.StopWatch

object TchBench {

  def apply(out: (String) => Unit) = {
    new Builder(out, None, None)
  }

  case class Builder(
    _out: (String) => Unit,
    _start: Option[String],
    _finish: Option[(StopWatch) => String]
  ) {

    def execute[A](f: => A)(implicit stopWatch: StopWatch): A = {
      _start.foreach(s => _out(s))
      stopWatch.start()
      val a = f
      stopWatch.stop()
      _finish.foreach(f => _out(f(stopWatch)))
      stopWatch.reset()
      a
    }

    def start(s: String): Builder = this.copy(_start = Some(s))
    def finish(f: (StopWatch) => String): Builder = this.copy(_finish = Some(f))
    def out(o: (String) => Unit): Builder = this.copy(_out = o)
  }
}