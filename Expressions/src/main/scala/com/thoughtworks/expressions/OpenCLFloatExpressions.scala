package com.thoughtworks.expressions

/**
  * @author 杨博 (Yang Bo)
  */
trait OpenCLFloatExpressions extends FloatExpressions with OpenCLExpressions {

  protected trait FloatCompanionApi extends super.FloatCompanionApi { this: FloatCompanion =>
    override def toCode(context: Context): NativeType.Code =
      NativeType.Code(accessor = NativeType.Accessor.Atom("float"))

  }

  type FloatCompanion <: (ValueCompanion with Any) with FloatCompanionApi

}
