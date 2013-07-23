package test.applicative

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import main.com.cattheory._
import main.com.cattheory.typeclasses.types.Types._
import main.com.cattheory.typeclasses.FunctorLikeList

//http://wiki.ifs.hsr.ch/SemProgAnTr/files/ApplicativeFunctorsInHaskell.pdf
@RunWith(classOf[JUnitRunner])
class ApplicativeTest extends FunSuite {

  def pureM[X](v: X)(implicit ev: ApplicativeLike[Maybe]) = ev.pure(v)

  def pureL[X](v: X)(implicit ev: ApplicativeLike[List]) = ev.pure(v)

  //pure id <*> u == u
  test("identity law for Maybe") {
    assert(pureM[Int => Int](identity _) <*> Just(3) === Just(3))

    assert(pureM[Int => Int](identity _) <*> HNothing === HNothing)
  }

  //p_composition x y = (pure (.) <*> pure (+x) <*>pure (*x) <*> y) == (pure (+x) <*> (pure (*x)<*> y))
  //pure (.) <*> u <*> v <*> w == u <*> (v <*> w)
  test("composition law for Maybe") {

    def f1(x: Int) = x + 10
    def f2(x: Int) = x * 5

    def compose(x: Int => Int)(y: Int => Int): Int => Int = x compose y

    val value = Just(3)

    assert(pureM(compose _) <*> pureM(f1) <*> pureM(f2) <*> value === pureM(f1 _) <*> (pureM(f2 _) <*> value))

  }

  test("homomorphism law for Maybe") {

    val f = (x: Int) => x + 99

    assert(pureM(f) <*> pureM(11) === pureM(f(11)))
  }

  //u <*> pure x == pure (\f -> f x) <*> u
  test("interchange law for Maybe") {

    val u = Just((x: Int) => x + 99)

    val f = (fun: Int => Int) => fun(2)

    assert(u <*> pureM(2) === pureM(f) <*> u)

  }

  //=========

  test("identity law for List") {
    assert(pureL[Int => Int](identity _) <*> List(1, 2, 3) === List(1, 2, 3))

    assert(pureL[Int => Int](identity _) <*> List() === List())
  }

   test("composition law for List") {

    def f1(x: Int) = x + 10
    def f2(x: Int) = x * 5

    def compose(x: Int => Int)(y: Int => Int): Int => Int = x compose y

    val value = List(1,2,3)

    assert(pureL(compose _) <*> pureL(f1) <*> pureL(f2) <*> value === pureL(f1 _) <*> (pureL(f2 _) <*> value))

  }

  test("homomorphism law for List") {

    val f = (x: Int) => x + 99

    assert(pureL(f) <*> pureL(11) === pureL(f(11)))
  }

  test("interchange law for List") {

    val u = List((x: Int) => x + 99)

    val f = (fun: Int => Int) => fun(2)

    assert(u <*> pureL(2) === pureL(f) <*> u)

  }

  
}