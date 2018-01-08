package com.thoughtworks.expressions

/**
  * @author 杨博 (Yang Bo)
  */
trait OpenCLBooleanExpressions extends OpenCLExpressions with BooleanExpressions {

  protected trait BooleanTypeApi extends super.BooleanTypeApi { this: BooleanType =>
    override def toCode(context: Context): Type.Code =
      Type.Code(accessor = Type.Accessor.Atom("bool"))

  }

  type BooleanType <: (ValueType with Any) with BooleanTypeApi

}
