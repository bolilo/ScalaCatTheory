package main.com.cattheory.typeclasses

import main.com.cattheory.typeclasses.types.Types.ApplicativeLike

trait AplicativeLikeList extends ApplicativeLike[List] {

  override def pure[A](a: A): List[A] = List(a)

  override def <*>[A, B](funs: List[A => B])(context: List[A]): List[B] = {
    for {
      fun <- funs
      x <- context
    } yield fun(x)
  }

}