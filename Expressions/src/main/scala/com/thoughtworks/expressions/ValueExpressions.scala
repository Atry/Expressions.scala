package com.thoughtworks.expressions

import com.thoughtworks.feature.Factory
import com.thoughtworks.feature.Factory.inject

/**
  * @author 杨博 (Yang Bo)
  */
trait ValueExpressions extends Expressions {

  protected trait ValueCompanionApi extends CompanionApi { this: ValueCompanion =>
    type JvmCompanion
  }

  /** @template */
  type ValueCompanion <: (Companion with Any) with ValueCompanionApi

}
