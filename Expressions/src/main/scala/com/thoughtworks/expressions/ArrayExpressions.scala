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

    protected trait ArrayCompanionApi extends CompanionApi {
      arrayCompanion: ArrayCompanion =>

      val operand0: Seq[Int]
      def shape: Seq[Int] = operand0

      protected trait TypedTermApi extends TermApi with ArrayCompanionApi.super.TypedTermApi {
        this: TypedTerm =>
        def isOutOfBound: BooleanTerm = ???

        def extract(implicit debuggingInformation: Implicitly[DebuggingInformation]): elementCompanion.TypedTerm = {
          Extract(this)
        }

      }

      type TypedTerm <: (ArrayTerm with Any) with TypedTermApi

      protected trait ExtractApi {
        val operand0: TypedTerm
      }

      type Extract <: elementCompanion.TypedTerm with ExtractApi

      @inject
      def Extract: Operator1[TypedTerm, Extract]

//      FIXME: Transform 的类型应该怎么定义
//      trait TransformApi { this: Transform =>
//        val operand0: ArrayTerm
//        val operand1: Array[Array[Int]]
//      }
//
//      type Transform <: TypedTerm with TransformApi
//      @inject
//      def Transform: Operator2[ArrayTerm, Array[Array[Int]], Transform]

    }

    trait ArrayTermApi {}

    type ArrayTerm <: (Term with Any) with ArrayTermApi

    /** @template */
    type ArrayCompanion <: (Companion with Any) with ArrayCompanionApi

    @inject
    def arrayFactory: Factory2[Implicitly[DebuggingInformation], Seq[Int], ArrayCompanion]

    def array(dimensions: Int*)(implicit debuggingInformation: Implicitly[DebuggingInformation]) = {
      arrayFactory.newInstance(debuggingInformation, dimensions)
    }

  }

  type ValueCompanion <: (Companion with Any) with ValueCompanionApi

}
