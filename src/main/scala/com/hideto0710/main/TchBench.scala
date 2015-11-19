package com.hideto0710.main

import org.apache.commons.lang3.time.StopWatch

sealed trait TBoolean
sealed trait TTrue extends TBoolean
sealed trait TFalse extends TBoolean
sealed trait BuilderMethods { type OutCalled <: TBoolean }

object TchBench {

  type UnusedBuilder = BuilderMethods { type OutCalled = TFalse }

  def apply[A](f: => A) = {
    new Builder(() => f, None, None, None): Builder[A, UnusedBuilder]
  }

  case class Builder[A, M <: BuilderMethods](
    _f: () => A,
    _out: Option[(String) => Unit],
    _start: Option[String],
    _finish: Option[(StopWatch) => String]
  ) {

    def execute(implicit stopWatch: StopWatch, t: M#OutCalled =:= TTrue): A = {
      _start.foreach(s => _out.get(s))
      stopWatch.start()
      val a = _f()
      stopWatch.stop()
      _finish.foreach(f => _out.get(f(stopWatch)))
      stopWatch.reset()
      a
    }

    def start(s: String): Builder[A, M] = this.copy(_start = Some(s))
    def finish(f: (StopWatch) => String): Builder[A, M] = this.copy(_finish = Some(f))
    def out(o: (String) => Unit): Builder[A, M{ type OutCalled = TTrue }] = this.copy(_out = Some(o))
  }
}