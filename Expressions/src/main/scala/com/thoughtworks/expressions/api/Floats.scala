package com.thoughtworks.expressions.api

import com.thoughtworks.expressions.Anonymous.Implicitly
import com.thoughtworks.feature.Factory.inject
import scala.language.higherKinds

/**
  * @author 杨博 (Yang Bo)
  */
trait Floats extends Values {
  type Category >: this.type <: Floats

  protected trait FloatExpressionApi extends ValueExpressionApi {
    type TermIn[C <: Category] = C#FloatTerm
    type TypeIn[C <: Category] = C#FloatType
  }

  protected trait FloatTermApi extends ValueTermApi with FloatExpressionApi { this: FloatTerm =>

  }

  type FloatTerm <: (ValueTerm with Any) with FloatTermApi

  protected trait FloatTypeApi extends ValueTypeApi with FloatExpressionApi {
    type JvmValue = Float
  }

  type FloatType <: (ValueType with Any) with FloatTypeApi

  @inject
  val float: Implicitly[FloatType]

}
