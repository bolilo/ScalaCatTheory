package test.functor

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import main.com.cattheory._
import main.com.cattheory.typeclasses._

import main.com.cattheory.typeclasses.types.Types._

@RunWith(classOf[JUnitRunner])
class FunctorTest extends FunSuite {

  type Fun[A, B, T[_]] = { def functorMap[B](f: A => B): T[B] }

  def testIdentity[A, B, T[A], X <% Fun[A, B, T]](x: X) = {
    x.functorMap(identity).equals(x)
  }

  test("identity law for Maybe") {

    assert(testIdentity(Just(99)) === true)
    assert(testIdentity(None) === true)
  }

  test("composition law for Maybe") {

    val f1 = (x: Int) => { x + 100 }
    val f2 = (x: Int) => Just(x + 200)

    val f1AndThenf2 = f1 andThen f2

    val j = Just(10)
    assert(j.functorMap(f1).functorMap(f2) === j.functorMap(f1AndThenf2))

    val n = None
    assert(n.functorMap(f1).functorMap(f2) === n.functorMap(f1AndThenf2))

  }

  test("identity laws for List") {

  }

  test("composition law for Kist") {

  }

}