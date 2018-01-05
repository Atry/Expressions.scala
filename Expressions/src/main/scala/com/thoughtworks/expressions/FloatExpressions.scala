package com.thoughtworks.expressions

import com.thoughtworks.feature.Factory
import com.thoughtworks.feature.Factory.inject

/**
  * @author 杨博 (Yang Bo)
  */
trait FloatExpressions extends ValueExpressions {

  protected trait FloatCompanionApi extends ValueCompanionApi { this: FloatCompanion =>
    type JvmCompanion = Float
  }

  /** @template */
  type FloatCompanion <: (ValueCompanion with Any) with FloatCompanionApi

  @inject
  protected def FloatCompanion: Factory.Factory1[DebuggingInformation, FloatCompanion]

  val float: FloatCompanion = FloatCompanion.newInstance(debuggingInformation)

  type FloatExpression = float.TypedExpression

}
