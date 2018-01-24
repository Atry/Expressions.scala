package com.thoughtworks.expressions.api
import scala.language.higherKinds

/**
  * @author 杨博 (Yang Bo)
  */
trait Values extends Terms {
  type Category >: this.type <: Values

  protected trait ValueApi extends TermApi {
    val `type`: ValueType
  }

  /** @template */
  type ValueTerm <: (Term with Any) with ValueApi

  protected trait ValueTypeApi extends TypeApi with ExpressionApi {
    type TermIn[C <: Category] <: C#ValueTerm
    type TypeIn[C <: Category] <: C#ValueType

    type JvmValue

    def literal(value: JvmValue): ThisTerm

    def parameter(id: Any): ThisTerm

  }

  type ValueType <: (Type with Any) with ValueTypeApi
}
