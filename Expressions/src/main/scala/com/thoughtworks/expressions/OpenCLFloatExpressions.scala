package com.thoughtworks.expressions

/**
  * @author 杨博 (Yang Bo)
  */
trait OpenCLFloatExpressions extends FloatExpressions with OpenCLExpressions {

  protected trait FloatCompanionApi extends super.FloatCompanionApi { this: FloatCompanion =>
    override def toCode(context: Context): Companion.Code =
      Companion.Code(accessor = Companion.Accessor.Atom("float"))

  }

  type FloatCompanion <: (ValueCompanion with Any) with FloatCompanionApi

}
