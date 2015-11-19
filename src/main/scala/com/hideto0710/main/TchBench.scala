package com.hideto0710.main

import org.apache.commons.lang3.time.StopWatch

object TchBench {

  def apply[A](f: => A, o: (String) => Unit) = {
    new Builder(()=>f, o, None, None): Builder[A]
  }

  case class Builder[A](
    _f: () => A,
    _out: (String) => Unit,
    _start: Option[String],
    _finish: Option[(StopWatch) => String]
  ) {

    def execute(implicit stopWatch: StopWatch): A = {
      _out(_start.get)
      stopWatch.start()
      val a = _f()
      stopWatch.stop()
      _out(_finish.get(stopWatch))
      stopWatch.reset()
      a
    }

    def start(s: String): Builder[A] = this.copy(_start = Some(s))
    def finish(f: (StopWatch) => String): Builder[A] = this.copy(_finish = Some(f))
  }
}