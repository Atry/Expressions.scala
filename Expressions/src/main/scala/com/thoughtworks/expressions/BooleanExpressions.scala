package com.thoughtworks.expressions

import com.thoughtworks.feature.Factory
import com.thoughtworks.feature.Factory.{Factory1, inject}

/**
  * @author 杨博 (Yang Bo)
  */
trait BooleanExpressions extends ValueExpressions {

  protected trait BooleanCompanionApi extends ValueCompanionApi { this: BooleanCompanion =>
    type JvmCompanion = Boolean
  }

  /** @template */
  type BooleanCompanion <: (ValueCompanion with Any) with BooleanCompanionApi

  @inject
  protected def BooleanCompanion: Factory1[DebuggingInformation, BooleanCompanion]

  type BooleanExpression = boolean.TypedExpression

  val boolean: BooleanCompanion = BooleanCompanion.newInstance(debuggingInformation)
}
