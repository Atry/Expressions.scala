package com.thoughtworks.expressions

import com.dongxiguo.fastring.Fastring
import com.dongxiguo.fastring.Fastring.Implicits._
import shapeless.ops.nat.ToInt
import shapeless.{Nat, Sized, Succ}

import scala.collection.{AbstractSeq, IndexedSeqOptimized}
import scala.language.higherKinds
//object OpenCLArrayExpressions {
//
//
//
//  /** A n-dimensional affine transform that performs a linear mapping from n-dimensional coordinates to other n-dimensional coordinates
//    *
//    * This [[TransformationMatrix]] is similar to [[java.awt.geom.AffineTransform]],
//    * except this [[TransformationMatrix]] is generic for any n-dimensional coordinates, not only 2D coordinates.
//    */
//  type TransformationMatrix[NumberOfDimensions <: Nat] =
//    Sized[IndexedSeq[Sized[IndexedSeq[Double], Succ]], NumberOfDimensions]
//
//  object TransformationMatrix {
//    def identity[NumberOfDimensions <: Nat: ToInt]: TransformationMatrix = {
//      val numberOfDimensions = ToInt.apply()
//      type Row = Sized[IndexedSeq[Double], Succ]
//      val matrixData: IndexedSeq[Row] = new AbstractSeq[Row] with IndexedSeq[Row] {
//        def length: Int = numberOfDimensions
//        def apply(y: Int): Row = {
//          val rowData: IndexedSeq[Double] = new AbstractSeq[Double] with IndexedSeq[Double] {
//            def length: Int = numberOfDimensions + 1
//            def apply(x: Int): Double = if (x == y) 1.0 else 0.0
//          }
//          Sized.wrap(rowData)
//        }
//      }
//      Sized.wrap(matrixData)
//    }
//  }
//
//}

/**
  * @author 杨博 (Yang Bo)
  */
trait OpenCLArrayExpressions extends ArrayExpressions with OpenCLBooleanExpressions {

  protected trait CompanionApi extends super[ArrayExpressions].CompanionApi with super[OpenCLBooleanExpressions].CompanionApi {
    this: Companion =>

  }

  type Companion <: (Named with Any) with CompanionApi
  protected trait ValueCompanionApi extends CompanionApi with super.ValueCompanionApi { elementCompanion: ValueCompanion =>

    protected trait ArrayCompanionApi
        extends super.ArrayCompanionApi
        with CompanionApi { arrayCompanion: ArrayCompanion =>

      override def toCode(context: Context): Companion.Code = {
        val element = context.get(elementCompanion)
        Companion.Code(
          globalDeclarations = Fastring.empty,
          globalDefinitions =
            fast"typedef global ${element.packed} (* $name)${for (size <- arrayCompanion.shape) yield fast"[$size]"};",
          Companion.Accessor.Atom(name)
        )
      }

      protected trait TypedExpressionApi extends ArrayExpressionApi with super[CompanionApi].TypedExpressionApi with super[ArrayCompanionApi].TypedExpressionApi {
        this: TypedExpression =>
//        def matrix: TransformationMatrix
      }

      type TypedExpression <: (ArrayExpression with Any) with TypedExpressionApi

      protected trait ExtractApi extends super.ExtractApi with elementCompanion.TypedExpressionApi {
        this: elementCompanion.TypedExpression =>
        def toCode(context: Context): Expression.Code = ???
      }

      type Extract <: (elementCompanion.TypedExpression with Any) with ExtractApi

      protected trait IdentifierApi extends super.IdentifierApi with TypedExpressionApi { this: Identifier =>
//        def matrix: TransformationMatrix = TransformationMatrix.identity
      }

      type Identifier <: (TypedExpression with Any) with IdentifierApi

    }

    type ArrayCompanion <: (Companion with Any) with ArrayCompanionApi

  }
  type ValueCompanion <: (Companion with Any) with ValueCompanionApi

}
