package com.thoughtworks.expressions

import com.thoughtworks.expressions.api.{Arrays, Floats}
import com.thoughtworks.expressions.tree.AllTrees
import com.thoughtworks.feature.Factory
import org.scalatest.{FreeSpec, Matchers}

/**
  * @author 杨博 (Yang Bo)
  */
class OpenCLExpressionsSpec extends FreeSpec with Matchers {

  "api" ignore {

    val category1: AllTrees { type Category = Arrays with Floats } = {
      ???
    }

    val category2: AllTrees { type Category = Arrays with Floats } = {
      ???
    }

    def foo(e1: category1.FloatTerm): category2.FloatTerm = {
      e1.in(category2)
    }

//    type T = _1.`type`.TermIn[category2.type] forSome {
//      val _1: category1.ArrayTerm {
//        val `type`: category1.ArrayType { val elementType: category1.FloatTerm#TypeIn[category2.type] }
//      }
//    }

    def bar(e1: category1.array.TermOf[category1.FloatTerm]): category2.array.TermOf[category2.FloatTerm] = {

      val i: e1.TermIn[category2.type] = e1.in(category2)

      e1.in(category2)
    }
  }

//  "id" in {
//    val category1 = {
//      Factory[AllTrees].newInstance()
//    }
//
//    val category2 = {
//      Factory[AllTrees].newInstance()
//    }
//
//    def foo(e1: category1.FloatTerm): category2.FloatTerm = {
//      e1.in(category2)
//    }
//
//    def bar(e1: category1.ArrayTerm { type Element = category1.FloatTerm })
//      : category2.ArrayTerm { type Element = category2.FloatTerm } = {
//      e1.in(category2)
//    }
//
//  }
}
