package com.thoughtworks.expressions

import com.thoughtworks.expressions.Anonymous.Implicitly
import com.thoughtworks.feature.Factory.{Factory1, Factory2, Factory3, inject}
import shapeless.Lazy

/**
  * @author 杨博 (Yang Bo)
  */
trait Expressions extends Debugging {

  object Operator0 {
    implicit def operator0[Out](implicit factory: Factory1[Implicitly[DebuggingInformation], Out]): Operator0[Out] =
      new Operator0[Out] {
        def apply()(implicit debuggingInformation: Implicitly[DebuggingInformation]): Out = {
          factory.newInstance(debuggingInformation)
        }
      }
  }

  trait Operator0[Out] {
    def apply()(implicit debugging: Implicitly[DebuggingInformation]): Out
  }

  object Operator1 {
    implicit def operator1[Operand0, Out](
        implicit factory: Factory2[Implicitly[DebuggingInformation], Operand0, Out]): Operator1[Operand0, Out] =
      new Operator1[Operand0, Out] {
        def apply(operand0: Operand0)(implicit debuggingInformation: Implicitly[DebuggingInformation]): Out = {
          factory.newInstance(debuggingInformation, operand0)
        }
      }
  }

  trait Operator1[Operand0, Out] {
    def apply(operand0: Operand0)(implicit debuggingInformation: Implicitly[DebuggingInformation]): Out
  }

  object Operator2 {
    implicit def operator2[Operand0, Operand1, Out](
        implicit factory: Factory3[Implicitly[DebuggingInformation], Operand0, Operand1, Out])
      : Operator2[Operand0, Operand1, Out] =
      new Operator2[Operand0, Operand1, Out] {
        def apply(operand0: Operand0, operand1: Operand1)(
            implicit debuggingInformation: Implicitly[DebuggingInformation]): Out = {
          factory.newInstance(debuggingInformation, operand0, operand1)
        }
      }
  }

  trait Operator2[Operand0, Operand1, Out] {
    def apply(operand0: Operand0, operand1: Operand1)(
        implicit debuggingInformation: Implicitly[DebuggingInformation]): Out
  }

  protected trait TermApi {
    val `type`: Companion
  }

  /** @template */
  type Term <: (Expression with Any) with TermApi

  /** @template */
  protected type TermCompanion <: AnyRef

  @inject
  protected def TermCompanion(): Implicitly[TermCompanion]

  val Term: TermCompanion = TermCompanion()

  protected trait CompanionApi extends ExpressionApi { this: Companion =>

    protected trait TypedTermApi extends TermApi {
      val `type`: CompanionApi.this.type = CompanionApi.this
    }

    type TypedTerm <: (Term with Any) with TypedTermApi

    /** @template */
    type Identifier <: TypedTerm

    // FIXME: Some identifiers need additional settings,
    // so the arity may be not nullary,
    // and this method will be removed then.
    @inject
    def Identifier: Operator0[Identifier]

  }

  /** @template */
  type Companion <: (Expression with Any) with CompanionApi // TODO: Rename to Companion

  /** @template */
  protected type TypeCompanion <: AnyRef // TODO: Rename to TypeCompanion

  @inject
  protected def TypeCompanion(): Implicitly[TypeCompanion]

  val Companion: TypeCompanion = TypeCompanion()

}
