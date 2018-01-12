package com.thoughtworks.expressions
import scala.language.higherKinds

/**
  * @author 杨博 (Yang Bo)
  */
trait Expressions {
  type ExportCategory <: Expressions

  trait ExpressionApi {
    def export(category: ExportCategory): category.Expression
  }

  type Expression <: ExpressionApi

  type Term <: Expression

  trait TypeApi {
    type JvmType
    type TypedTerm <: Term

    def literal(value: JvmType): TypedTerm
  }

}

trait FloatExpressions extends Expressions {
  type ExportCategory <: FloatExpressions

  trait FloatTermApi extends ExpressionApi {
    def export(category: ExportCategory): category.FloatTerm
  }
  type FloatTerm <: Term with FloatTermApi

  trait FloatTypeApi {
    type JvmType = Float
    type TypedTerm <: FloatTerm
  }

  type Type <: Expression
  type FloatType <: Type with FloatTypeApi
  val float: FloatType

}
