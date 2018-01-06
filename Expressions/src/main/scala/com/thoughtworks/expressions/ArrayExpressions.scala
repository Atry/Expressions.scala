package com.thoughtworks.expressions

import com.thoughtworks.expressions.Anonymous.Implicitly
import com.thoughtworks.feature.Factory
import com.thoughtworks.feature.Factory.{Factory1, Factory2, inject}

import scala.language.higherKinds

/**
  * @author 杨博 (Yang Bo)
  */
trait ArrayExpressions extends BooleanExpressions {

  protected trait ValueCompanionApi extends super.ValueCompanionApi { elementCompanion: ValueCompanion =>
//
//    protected trait ArrayCompanionApi   {
//      arrayCompanion: ArrayCompanion =>
//
//      val operand0: Seq[Int]
//      def shape: Seq[Int] = operand0
//
//      protected trait TypedExpressionApi {
//        this: TypedExpression =>
//        def isOutOfBound: BooleanExpression = ???
//
//        def extract(implicit debuggingInformation: Implicitly[DebuggingInformation]): elementCompanion.TypedExpression = {
//          Extract(this)
//        }
//
//      }
//
//      type TypedExpression <: (ArrayExpression with Any) with TypedExpressionApi
//
//      protected trait ExtractApi {
//        val operand0: TypedExpression
//      }
//
//      type Extract <: elementCompanion.TypedExpression with ExtractApi
//
//      @inject
//      def Extract: Operator1[TypedExpression, Extract]
//
////      FIXME: Transform 的类型应该怎么定义
////      trait TransformApi { this: Transform =>
////        val operand0: ArrayExpression
////        val operand1: Array[Array[Int]]
////      }
////
////      type Transform <: TypedExpression with TransformApi
////      @inject
////      def Transform: Operator2[ArrayExpression, Array[Array[Int]], Transform]
//
//    }
//
//    trait ArrayExpressionApi {}
//
//    type ArrayExpression <: (Expression with Any) with ArrayExpressionApi
//
//    /** @template */
//    type ArrayCompanion <: (Companion with Any) with ArrayCompanionApi
//
//    @inject
//    def arrayFactory: Factory2[Implicitly[DebuggingInformation], Seq[Int], ArrayCompanion]
//
//    def array(dimensions: Int*)(implicit debuggingInformation: Implicitly[DebuggingInformation]) = {
//      arrayFactory.newInstance(debuggingInformation, dimensions)
//    }
//
  }

  type ValueCompanion <: (Named with Any) with ValueCompanionApi

}
