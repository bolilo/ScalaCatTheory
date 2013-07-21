package examples

import main.com.cattheory._
import main.com.cattheory.typeclasses.types.Types._
import main.com.cattheory.typeclasses._
//http://stackoverflow.com/questions/7830731/parameter-type-in-structural-refinement-may-not-refer-to-an-abstract-type-defin
object FunctorExample {

  def main(args: Array[String]) {

    val v: Maybe[Int] = Just(99)

    println(v.functorMap((x: Int) => x + 11)) //prints Just(110)

    println(Just(9).functorMap((x: Int) => x + 11)) //prints Just(20) 

    println(List(1, 2, 3).functorMap((x: Int) => x + 88)) //prints List(89,90,91)
  }

}