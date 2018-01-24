package com.thoughtworks.expressions.api

import com.thoughtworks.expressions.Anonymous.Implicitly
import com.thoughtworks.feature.Factory.inject

/**
  * @author 杨博 (Yang Bo)
  */
trait Arrays extends Values {
  type Category >: this.type <: Arrays

  protected trait ValueApi extends super.ValueApi { thisValue: ValueTerm =>
    def fill(shape: Int*): ArrayTerm {
      val `type`: ArrayType {
        val elementType: thisValue.ThisType
      }
    }
  }

  override type ValueTerm <: (Term with Any) with ValueApi

  protected trait ArrayApi extends TermApi {
    type ElementTerm = `type`.elementType.ThisTerm
    type ElementType = `type`.elementType.ThisType

    def shape: Array[Int]

    def extract: ElementTerm

    val `type`: ArrayType

  }

  type ArrayTerm <: (Term with Any) with ArrayApi

  protected trait ArrayTypeApi extends TypeApi { thisArrayType =>
    val elementType: ValueType

    type TermIn[C <: Category] = C#ArrayTerm {
      val `type`: C#ArrayType {
        val elementType: thisArrayType.elementType.TypeIn[C]
      }
    }

    type TypeIn[C <: Category] = C#ArrayType {
      val elementType: thisArrayType.elementType.TypeIn[C]
    }
  }
  type ArrayType <: (Type with Any) with ArrayTypeApi

  @inject
  val array: Implicitly[ArrayCompanion]

  protected trait ArrayCompanionApi {

    type TermOf[ElementExpression <: ExpressionApi] = ArrayTerm {
      val `type`: ArrayType {
        val elementType: ElementExpression#ThisType
      }
    }

    def parameter(id: Any, elementType0: ValueType, shape: Int*): ArrayTerm {
      val `type`: ArrayType {
        val elementType: elementType0.ThisType
      }
    }

  }

  type ArrayCompanion <: ArrayCompanionApi

}
