package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.Functor

trait FunctorLikeList extends Functor[List] {

  def functorMap[A, B](f: A => B): List[A] => List[B] = {

    def fun(x: List[A]): List[B] = x.map(f)
    fun
  }
}
  