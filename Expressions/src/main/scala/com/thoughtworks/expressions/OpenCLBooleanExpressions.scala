package com.thoughtworks.expressions

/**
  * @author 杨博 (Yang Bo)
  */
trait OpenCLBooleanExpressions extends OpenCLExpressions with BooleanExpressions {

  protected trait BooleanCompanionApi extends super.BooleanCompanionApi { this: BooleanCompanion =>
    override def toCode(context: Context): Companion.Code =
      Companion.Code(accessor = Companion.Accessor.Atom("bool"))

  }

  type BooleanCompanion <: (ValueCompanion with Any) with BooleanCompanionApi

}
