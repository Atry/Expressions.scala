package com.thoughtworks.expressions

import com.thoughtworks.feature.Factory
import com.thoughtworks.feature.Factory.inject

/**
  * @author 杨博 (Yang Bo)
  */
trait ValueExpressions extends Expressions {

  protected trait ValueCompanionApi { this: ValueCompanion =>
    type JvmCompanion

    type TypedExpression <: Expression

    /** @template */
    type Identifier <: TypedExpression

    // FIXME: Some identifiers need additional settings,
    // so the arity may be not nullary,
    // and this method will be removed then.
    @inject
    def Identifier: Operator0[Identifier]

  }

  /** @template */
  type ValueCompanion <: (Named with Any) with ValueCompanionApi

}
