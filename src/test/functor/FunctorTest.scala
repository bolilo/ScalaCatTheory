package test.functor

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import main.com.cattheory._
import main.com.cattheory.typeclasses.types.Types._
import main.com.cattheory.typeclasses.FunctorLikeList

@RunWith(classOf[JUnitRunner])
class FunctorTest extends FunSuite {

  //type Fun[A, B, T[_]] = { def functorMap[B](f: A => B): T[B] }

  //def testIdentity[A, B, T[_], X <% Fun[A, B, T]](x: X) = {
  //  x.functorMap(identity).equals(x)
  //}

  val f1 = (x: Int) => { x + 100 }
  val f2 = (x: Int) => Just(x + 200)

  val f1AndThenf2 = f1 andThen f2

  test("identity law for Maybe") {
    assert(Just(99).functorMap(identity) === Just(99))
    assert(HNothing.functorMap(identity) === HNothing)
  }

  test("composition law for Maybe") {
    val j = Just(10)
    assert(j.functorMap(f1).functorMap(f2) === j.functorMap(f1AndThenf2))

    val n = HNothing
    assert(n.functorMap(f1).functorMap(f2) === n.functorMap(f1AndThenf2))
  }

  test("identity laws for List") {
    assert(List(1, 2, 3).functorMap(identity) === List(1, 2, 3))
    assert(List().functorMap(identity) === List())
  }

  test("composition law for List") {
    val empty = List()
    assert(empty.functorMap(f1).functorMap(f2) === empty.functorMap(f1AndThenf2))

    val noEmpty = List(1, 2, 3)
    assert(noEmpty.functorMap(f1).functorMap(f2) === noEmpty.functorMap(f1AndThenf2))
  }
}