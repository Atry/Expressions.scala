package com.thoughtworks.expressions.api

import com.thoughtworks.feature.Factory.inject

import scala.language.higherKinds

/**
  * @author 杨博 (Yang Bo)
  */
trait Terms {
  type Category >: this.type <: Terms

  protected trait ExpressionApi {
    type TermIn[C <: Category] <: C#Term
    type TypeIn[C <: Category] <: C#Type

    type ThisTerm = TermIn[Terms.this.type]
    type ThisType = TypeIn[Terms.this.type]
  }

  protected trait TermApi extends ExpressionApi {

    val `type`: Type
    type TermIn[C <: Category] = `type`.TermIn[C]
    type TypeIn[C <: Category] = `type`.TypeIn[C]

  }

  type Term <: TermApi

  protected trait TypeApi extends ExpressionApi {

    @inject
    def asThisType: this.type <:< ThisType
  }

  type Type <: TypeApi

}
