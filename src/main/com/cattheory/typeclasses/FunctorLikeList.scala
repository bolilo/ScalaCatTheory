package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.Functor

trait FunctorLikeList extends Functor[List] {

  override def functorMap[A, B](f: A => B)(context: List[A]): List[B] = context.map(f)

}
  