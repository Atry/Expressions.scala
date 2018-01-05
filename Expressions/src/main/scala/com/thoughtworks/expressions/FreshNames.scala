package com.thoughtworks.expressions

import java.util.concurrent.atomic.AtomicInteger

/**
  * @author 杨博 (Yang Bo)
  */
trait FreshNames extends Debugging {
  // FIXME: Remove this plugin. `freshName` function should be provided by the OpenCL Context.

  protected val freshNameSeed = new AtomicInteger()

  protected def freshName(prefix: String): String = {
    val freshId: Int = freshNameSeed.getAndIncrement()
    raw"""${prefix}_$freshId"""

  }

  protected trait NamedApi extends super.NamedApi {
    private lazy val _name: String = freshName(super.name)
    override def name: String = _name
  }

  type Named <: NamedApi

}
