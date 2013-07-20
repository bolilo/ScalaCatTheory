package examples

import main.com.cattheory._
import main.com.cattheory.typeclasses.types.Types._
import main.com.cattheory.typeclasses._
import main.com.cattheory.typeclasses.FunctorLikeMaybe

object FunctorExample {

  def main(args: Array[String]) {

    val v: Maybe[Int] = Just(99)

    println(v.functorMap[Int]((x: Int) => x + 11)) //prints Just(110)


    println(Just(9).functorMap((x: Int) => x + 11)) //prints Just(20) 

    println(List(1, 2, 3).functorMap((x: Int) => x + 88)) //prints List(89,90,91)

  }

}