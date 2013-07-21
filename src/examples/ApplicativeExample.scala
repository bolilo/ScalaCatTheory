package examples

import main.com.cattheory._
import main.com.cattheory.typeclasses.types.Types._
import main.com.cattheory.typeclasses._

object ApplicativeExample {

  def main(args: Array[String]) {

    val m: Just[Int => Int] = Just((x: Int) => x + 1)

    println(m <*> (Just(2)))

    println(List((str: String) => str.length(), (str: String) => 100+str.length())<*>List("ab", "abc"))

  }
}